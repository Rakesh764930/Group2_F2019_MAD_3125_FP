package com.example.employeepayrollapplication.Fragments;

import android.arch.lifecycle.Lifecycle;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.employeepayrollapplication.R;
import com.example.employeepayrollapplication.models.CommissionBasedPartTime;
import com.example.employeepayrollapplication.models.Singleton;
import com.example.employeepayrollapplication.models.Vehicle;
import com.harsh.group6_w2019_mad3125_fp.interfaces.DataFromPartTimeFragment;

import model.Car;
import model.CommissionBasedPartTime;
import model.MotorCycle;
import model.SingleToneExample;
import model.abstracts.Vehicle;
import model.enums.Gender;

public class CommissionBasedPartTimeFragment extends Fragment implements DefaultPartTimeInterface
{

    TextView name;
    TextView age;
    TextView ratePerHour;
    TextView numberOfHours;
    TextView dateOfBirth;
    RadioGroup vehicle;

    TextView commission;
    Button addParttimeCommissionBasedEmployee;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.commission_based_fragment_part_time_layout, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);




        this.commission = view.findViewById(R.id.text_commission);

        this.addParttimeCommissionBasedEmployee = view.findViewById(R.id.btn_add_commission_based);
        this.addParttimeCommissionBasedEmployee.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if( !commission.getText().toString().isEmpty() && !ratePerHour.getText().toString().isEmpty() && !numberOfHours.getText().toString().isEmpty() && !name.getText().toString().isEmpty() &&  !age.getText().toString().isEmpty() && !(gender.getCheckedRadioButtonId() == -1)) {
                    int commission_int = Integer.parseInt(commission.getText().toString());
                    int rate_int = Integer.parseInt(ratePerHour.getText().toString());
                    float hours_float = Float.parseFloat(numberOfHours.getText().toString());
                    String name_string = name.getText().toString();
                    int age_int = Integer.parseInt(age.getText().toString().substring(6));

                    Vehicle vehicle = null;
                    switch (vehicle.getCheckedRadioButtonId()) {
                        case R.id.rbCar:
                            vehicle = new Car("", "", "", 0);
                            break;
                        case R.id.rbMotorCycle:
                            vehicle= new MotorCycle("", "", "", 0);
                            break;

                    }



                    Singleton.getObj().addIntoList(new CommissionBasedPartTime(commission_int, rate_int, hours_float, name_string, age_int, gender_enum, vehicle));
                    Toast.makeText(getActivity(), "Employee Added", Toast.LENGTH_LONG).show();
                    commission.setText(null);
                    ratePerHour.setText(null);
                    numberOfHours.setText(null);
                    name.setText(null);
                    age.setText(null);
                    dateOfBirth.setText(("DateOfBirth : YYYY/MM/DD"));

                    vehicle.clearCheck();

                }
                else
                {
                    Toast.makeText(getActivity(), "No field can be empty and unselected" , Toast.LENGTH_LONG).show();
                }


            }
        });

    }

    @Override
    public void DefaultPartTimeFields(TextView name, TextView age, TextView ratePerHour, TextView numberOfHours , TextView dateOfBirth , RadioGroup vehicle) {
        this.name = name;
        this.age = age;

        this.ratePerHour = ratePerHour;
        this.numberOfHours = numberOfHours;
        this.dateOfBirth = dateOfBirth;
        this.vehicle = vehicle;
    }
}
