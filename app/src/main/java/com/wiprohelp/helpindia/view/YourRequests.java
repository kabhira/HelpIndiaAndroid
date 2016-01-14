package com.wiprohelp.helpindia.view;

        import android.content.Context;
        import android.content.res.Resources;
        import android.os.Bundle;
        import android.support.v7.app.AppCompatActivity;
        import android.support.v7.widget.Toolbar;
        import android.view.LayoutInflater;
        import android.view.View;
        import android.view.ViewGroup;
        import android.widget.ArrayAdapter;
        import android.widget.ImageView;
        import android.widget.ListView;
        import android.widget.TextView;

        import com.wiprohelp.helpindia.R;

/**
 * Created by SH341636 on 1/7/2016.
 */
public class YourRequests extends AppCompatActivity{
    ListView myRequest;
    int[] images = {R.drawable.depressed, R.drawable.medicine, R.drawable.stomach,
            R.drawable.dizzy,R.drawable.skin,R.drawable.fever,
            R.drawable.bleeding};
    String[] names;
    String[] appointment;
    String[] phone;
    String[] details;
    String[] condition;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_your_requests);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        myRequest = (ListView) findViewById(R.id.listview);
        Resources res = getResources();
        names = res.getStringArray(R.array.Names);
        appointment = res.getStringArray(R.array.appointment_time);
        phone = res.getStringArray(R.array.phone_number);
        details = res.getStringArray(R.array.details);
        condition = res.getStringArray(R.array.medical_condition);

        MyAdapter adapter = new MyAdapter(this,names,images,appointment,phone,details,
                condition);
        myRequest.setAdapter(adapter);
    }

    class myHolder{
        ImageView myImg;
        TextView myName;
        TextView myAppointment;
        TextView myPhone;
        TextView myDetails;
        TextView myCondition;

        myHolder(View view){
            myImg = (ImageView) view.findViewById(R.id.request_icon);
            myName = (TextView) view.findViewById(R.id.name_text);
            myAppointment = (TextView) view.findViewById(R.id.appointment_text);
            myPhone = (TextView) view.findViewById(R.id.phone_text);
            myDetails = (TextView) view.findViewById(R.id.details_text);
            myCondition = (TextView) view.findViewById(R.id.condition);
        }
    }

    class MyAdapter extends ArrayAdapter<String> {
        Context context;
        int[] images;
        String[] names;
        String[] appointment;
        String[] phone;
        String[] details;
        String[] condition;

        MyAdapter(Context c,String[] names,int[] imgs,String[] appointment,String[] phone,
                  String[] details,String[] condition){
            super(c,R.layout.single_row,R.id.name_text,names);
            this.context = c;
            this.names = names;
            this.images = imgs;
            this.appointment = appointment;
            this.phone = phone;
            this.details = details;
            this.condition = condition;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View row = convertView;
            myHolder holder = null;
            if(row == null){
                LayoutInflater inflater = (LayoutInflater)
                        context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                row = inflater.inflate(R.layout.single_row,parent,false);
                holder = new myHolder(row);
                row.setTag(holder);
            }
            else{
                holder = (myHolder) row.getTag();
            }
            TextView id = (TextView) row.findViewById(R.id.request_id);
            id.setText(Integer.toString(position));
            holder.myName.setText(names[position]);
            holder.myImg.setImageResource(images[position]);
            holder.myAppointment.setText(appointment[position]);
            holder.myPhone.setText(phone[position]);
            holder.myDetails.setText(details[position]);
            holder.myCondition.setText(condition[position]);
            return row;
        }
    }
}

