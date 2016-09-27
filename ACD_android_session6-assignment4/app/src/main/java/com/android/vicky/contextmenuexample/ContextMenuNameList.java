package com.android.vicky.contextmenuexample;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

public class ContextMenuNameList extends AppCompatActivity {
    ListView lstNameList;
    private final String[] nameArray ={
            "Pushpa",
            "Latha",
            "Arjun",
            "Kiran",
            "Arnav"
    };
    private final String[] numberArray={
            "9988778877",
            "9988778874",
            "9988778844",
            "7988778877",
            "9968778877"
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_context_menu_name_list);
        lstNameList = (ListView)findViewById(R.id.nameList);
        CustomAdapter adapter = new CustomAdapter(this,nameArray,numberArray);
        lstNameList.setAdapter(adapter);
        registerForContextMenu(lstNameList);
        /*lstNameList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String SelectedName = nameArray[+position];
                String SelectedNumber = numberArray[+position];
                Toast.makeText(getApplicationContext(), "Name : "+SelectedName+" No : "+SelectedNumber, Toast.LENGTH_SHORT).show();
            }
        });*/
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        menu.setHeaderTitle("Select The Action");
        menu.add(0, v.getId(), 0, "Call");
        menu.add(0,v.getId(),0,"SMS");
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        String name,phNumber;
        try{
            name = nameArray[info.position];
            phNumber = numberArray[info.position];

            if(item.getTitle().toString().equals("Call")){
//            Toast.makeText(ContextMenuNameList.this,"You Clicked On Call To "+name,Toast.LENGTH_SHORT).show();
                Intent callIntent = new Intent(Intent.ACTION_CALL);
                callIntent.setData(Uri.parse("tel:"+phNumber));
                startActivity(callIntent);
            }else if(item.getTitle().toString().equals("SMS")){
//            Toast.makeText(ContextMenuNameList.this,"You Clicked On SMS To "+phNumber,Toast.LENGTH_SHORT).show();
                Intent smsIntent = new Intent(Intent.ACTION_VIEW);
                smsIntent.setType("vnd.android-dir/mms-sms");
                smsIntent.putExtra("address", phNumber);
                smsIntent.putExtra("sms_body","Hey "+name+" ! How are you ?");
                startActivity(smsIntent);
            }else{
                return false;
            }
        }catch (SecurityException e){

        }

        return true;
    }
}
