package com.wiprohelp.helpindia.utilities;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.view.View;

import com.wiprohelp.helpindia.R;

import java.util.ArrayList;

/**
 * Created by AB335009 on 1/4/2016.
 */
public class SelectOptionAlert {

    private ArrayList<SelectOptionData> mContent;
    private ArrayList<String> itemsToShow;
    private ArrayList<SelectOptionData> mSelectedItems;
    private Context mContext;
    private MultiSelectListener multiSelectListener;
    private SingleSelectListener singleSelectListener;
    private View multiSelectActionView;

    public SelectOptionAlert(ArrayList<SelectOptionData> data, Context context){
        mContent = data;
        mContext = context;

        itemsToShow = new ArrayList<String>();
        for(SelectOptionData optionData : data){
            itemsToShow.add(optionData.optionName);
        }
    }

    public AlertDialog ceateSingleChoiceOption(){
        AlertDialog.Builder builder;
        builder = new AlertDialog.Builder(mContext);
        builder.setItems(itemsToShow.toArray(new CharSequence[itemsToShow.size()]), new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                // The 'which' argument contains the index position of the selected item
                if(singleSelectListener != null){
                    singleSelectListener.onSingleSelect(itemsToShow.get(which));
                }
            }
        });
        return builder.create();
    }

    public AlertDialog createMultipleChoiceOption(String title){
        mSelectedItems = new ArrayList<SelectOptionData>();
        AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
        builder.setTitle(title)
                .setMultiChoiceItems(itemsToShow.toArray(new CharSequence[itemsToShow.size()]), null, new DialogInterface.OnMultiChoiceClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which, boolean isChecked) {
                        if (isChecked) {
                            mSelectedItems.add(mContent.get(which));
                        }
                        else if (mSelectedItems.contains(mContent.get(which))) {
                            mSelectedItems.remove(mContent.get(which));
                        }
                    }
                })
                .setPositiveButton(R.string.alert_dialog_positive_text, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        // User clicked OK, so save the mSelectedItems results somewhere
                        // or return them to the component that opened the dialog
                        if(multiSelectListener != null){
                            String selectedString = "";
                            for (SelectOptionData optionData : mSelectedItems){
                                selectedString += optionData.optionName + ", ";
                            }
                            int lastIndex = selectedString.lastIndexOf(", ");
                            if(lastIndex != -1)
                                selectedString = new StringBuilder(selectedString).replace(lastIndex, lastIndex+1, "").toString();
                            multiSelectListener.onMultiSelectDone(selectedString, multiSelectActionView);
                        }
                    }
                });
        return builder.create();
    }

    public static class SelectOptionData{
        String optionName;
        public SelectOptionData(String optionName){
            this.optionName = optionName;
        }
    }

    public interface MultiSelectListener{
        void onMultiSelectDone(String selectedItems, View view);
    }

    public void setMultiSelectListener(MultiSelectListener multiSelectListener, View view) {
        this.multiSelectListener = multiSelectListener;
        this.multiSelectActionView = view;
    }

    public interface SingleSelectListener{
        void onSingleSelect(String selectedItem);
    }

    public void setSingleSelectListener(SingleSelectListener singleSelectListener) {
        this.singleSelectListener = singleSelectListener;
    }
}
