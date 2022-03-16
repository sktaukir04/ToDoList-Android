package com.example.todolist;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Rect;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class CountryList extends AppCompatActivity {
    private DBManager dbManager;
    private ListView listView;
    private SimpleCursorAdapter adapter;
    SearchView searchView;
    String id;

    final String[] from= new String[] {DatabaseHelper._ID,DatabaseHelper.SUBJECT,DatabaseHelper.DESC};
    final int[] to = new int[]{R.id.id,R.id.titles,R.id.desc};

    TextView idTextView,titleTextView,descTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dbManager=new DBManager(this);
        dbManager.open();
        Cursor cursor=dbManager.fetch();

        listView=findViewById(R.id.list_view);
        listView.setEmptyView(findViewById(R.id.empty));

//        searchView=(SearchView)findViewById(R.id.searchView);
//        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
//            @Override
//            public boolean onQueryTextSubmit(String query) {
//                return false;
//            }
//
//            @Override
//            public boolean onQueryTextChange(String newText) {
//                if (listView.getTextFilter()==newText)
//                adapter.getFilter().filter(newText);
//                return false;
//            }
//        });



        adapter=new SimpleCursorAdapter(this,R.layout.activity_view_record,cursor,from,to,0);
        adapter.notifyDataSetChanged();
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long Vid) {
                 idTextView =view.findViewById(R.id.id);
                 titleTextView =view.findViewById(R.id.titles);
                 descTextView =view.findViewById(R.id.desc);

                 id=idTextView.getText().toString();
                String title=titleTextView.getText().toString();
                String desc=descTextView.getText().toString();

                Intent modify_intent=new Intent(getApplicationContext(),ModifyCountryActivity.class);
                modify_intent.putExtra("title",title);
                modify_intent.putExtra("desc",desc);
                modify_intent.putExtra("id",id);
                startActivity(modify_intent);

            }
        });
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long Vid) {
            AlertDialog.Builder builder=new AlertDialog.Builder(CountryList.this);
            builder.setMessage("Delete this entry ?");
            builder.setCancelable(false);
            builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    Long _id= Long.valueOf(id);
                    dbManager.delete(_id);
                    Toast.makeText(CountryList.this, "Hogaya delete hai "+id, Toast.LENGTH_SHORT).show();
                }
            }).setNegativeButton("NO", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    Toast.makeText(CountryList.this, "Theek hai mat kar delete teri marzi...!", Toast.LENGTH_SHORT).show();
                }
            });
            AlertDialog alertDialog=builder.create();
            alertDialog.setTitle("Delete Data");
            alertDialog.show();
                return false;
            }
        });
        if (listView.getCount()==0){
            dbManager.clearDatabse();
        }


    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu);
        MenuItem searchItem=menu.findItem(R.id.search_list);
        SearchView searchView= (SearchView) searchItem.getActionView();
//        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
//            @Override
//            public boolean onQueryTextSubmit(String query) {
//                return false;
//            }
//
//            @Override
//            public boolean onQueryTextChange(String newText) {
//                adapter.getFilter();
//                return false;
//            }
//        });
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id=item.getItemId();
        if (id==R.id.add_Record){
            Intent add_mem =new Intent(this,AddCountryActivity.class);
            startActivity(add_mem);
        }
        if (id==R.id.dialog_info){
//            AlertDialog.Builder builder=new AlertDialog.Builder(this);
//            builder.setMessage("Did you like the Aoplication ?");
//            builder.setCancelable(false);
//            builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
//                @Override
//                public void onClick(DialogInterface dialog, int which) {
//                    Toast.makeText(CountryList.this, "Theek hai", Toast.LENGTH_SHORT).show();
//                }
//            }).setNegativeButton("NO", new DialogInterface.OnClickListener() {
//                @Override
//                public void onClick(DialogInterface dialog, int which) {
//                    Toast.makeText(CountryList.this, "Theek nhi hai", Toast.LENGTH_SHORT).show();
//                }
//            });
//            AlertDialog alertDialog=builder.create();
//            alertDialog.setTitle("Aur bata");
//            alertDialog.show();
            Rect displayRectangle=new Rect();
            Window window=CountryList.this.getWindow();
            window.getDecorView().getWindowVisibleDisplayFrame(displayRectangle);
            final AlertDialog.Builder builder= new AlertDialog.Builder(this,R.style.CustomAlertDialog);
            ViewGroup viewGroup=findViewById(R.id.content);
            View dialogView = LayoutInflater.from(getApplicationContext()).inflate(R.layout.dialog_cusstom,viewGroup,false);
            dialogView.setMinimumWidth((int)(displayRectangle.width() * 1f));
            dialogView.setMinimumHeight((int)(displayRectangle.height() * 1f));
            builder.setView(dialogView);
            final AlertDialog alertDialog = builder.create();
            Button buttonOk=dialogView.findViewById(R.id.buttonOk);
            buttonOk.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    alertDialog.dismiss();
                }
            });
            alertDialog.show();


        }
        
        return super.onOptionsItemSelected(item);
    }


}