package com.daclink.gymlog_v_sp22;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.daclink.gymlog_v_sp22.DB.AppDataBase;
import com.daclink.gymlog_v_sp22.DB.GymLogDAO;
import com.daclink.gymlog_v_sp22.databinding.ActivityMainBinding;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;//variable to add widget binding in oncreate
    //binding object variable
    private TextView mMainDisplay;

    private EditText mExercise;
    private EditText mWeight;
    private EditText mReps;

    private Button mSubmit;

    private GymLogDAO mGymLogDAO;
    //entry point into database;DAO object

    private List<GymLog> mGymLogList;
    //list to hold queries

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        //links textview/edittext/button to mainactivity
        setContentView(binding.getRoot());
        mMainDisplay = binding.mainGymLogDisplay;
        mExercise = binding.mainExerciseEditText;
        mWeight = binding.mainWeightEditText;
        mReps = binding.mainRepsEditText;
        mSubmit = binding.mainSubmitButton;
        mMainDisplay.setMovementMethod(new ScrollingMovementMethod());
        //allows scrolling

        mGymLogDAO = Room.databaseBuilder(this, AppDataBase.class,AppDataBase.DATABASE_NAME)
                .allowMainThreadQueries()
                .build()
                .GymLogDAO();
        //allows main thread queries; not advisable for complicated queries because main thread can
        //halt and crash; allows access to database object

        refreshDisplay();
        mSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                submitGymLog();
                refreshDisplay();
            }
        });
    }//end oncreate

    private void submitGymLog(){
        String exercise = mExercise.getText().toString();
        double weight = Double.parseDouble(mWeight.getText().toString());
        int reps = Integer.parseInt(mReps.getText().toString());
        GymLog log = new GymLog(exercise,weight,reps);
        mGymLogDAO.insert(log);
    }

   private void refreshDisplay(){
       mGymLogList = mGymLogDAO.getGymLogs();
       if( ! mGymLogList.isEmpty() ){
           StringBuilder sb = new StringBuilder();
           for(GymLog log : mGymLogList){
               sb.append(log.toString());
           }
           mMainDisplay.setText(sb.toString());
       }else{
           mMainDisplay.setText(R.string.no_logs_message);
       }
   }//end refreshDisplay()


}//end mainactivity