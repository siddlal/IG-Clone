package com.example.ig_clone;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SaveCallback;


/**
 * A simple {@link Fragment} subclass.
 */
public class ProfileTab extends Fragment {
    private EditText edtProfileName,edtBio,edtHobbies,edtProfession,edtFavSport;
    private Button btnUpdate;



    public ProfileTab() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_profile_tab, container, false);
        edtProfileName = view.findViewById(R.id.edtProfileName);
        edtProfession = view.findViewById(R.id.edtProfession);
        edtBio = view.findViewById(R.id.edtBio);
        edtHobbies = view.findViewById(R.id.edtHobbies);
        edtFavSport = view.findViewById(R.id.edtFavSport);

        btnUpdate = view.findViewById(R.id.btnUpdate);
        final ParseUser parseUser = ParseUser.getCurrentUser();// to get the reference from the current user.

        if(parseUser.get("profileName") == null){
            edtProfileName.setText("");
        }else{
            edtProfileName.setText(parseUser.get("profileName").toString());
        }


        if(parseUser.get("ProfileBio") == null){
            edtBio.setText("");
        }else{
            edtBio.setText(parseUser.get("ProfileBio").toString());
        }
        if(parseUser.get("ProfileProfession") == null){
            edtProfession.setText("");
        }else{
            edtProfession.setText(parseUser.get("ProfileProfession").toString());
        }
        if(parseUser.get("profileHobby") == null){
            edtHobbies.setText("");
        }else{
            edtHobbies.setText(parseUser.get("profileHobby").toString());
        }
        if(parseUser.get("FavSport") == null){
            edtFavSport.setText("");
        }else{
            edtFavSport.setText(parseUser.get("FavSport").toString());
        }


//        edtProfileName.setText(parseUser.get("profileName") + " ");
//        edtBio.setText(parseUser.get("ProfileBio") + " ");
//        edtProfession.setText(parseUser.get("ProfileProfession") + " ");
//        edtHobbies.setText(parseUser.get("profileHobby") + " ");
//        edtFavSport.setText(parseUser.get("FavSport") + " ");

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                parseUser.put("profileName",edtProfileName.getText().toString());
                parseUser.put("ProfileBio",edtBio.getText().toString());
                parseUser.put("ProfileProfession",edtProfession.getText().toString());
                parseUser.put("profileHobby",edtHobbies.getText().toString());
                parseUser.put("FavSport",edtFavSport.getText().toString());

                parseUser.saveInBackground(new SaveCallback() {
                    @Override
                    public void done(ParseException e) {
                        if(e == null){

                            Toast.makeText(getContext(),"Information is updated",Toast.LENGTH_SHORT).show();
                            //we need to pass getContent in place of ProfileTab as it is a fragment not an activity.
                        }
                        else{
                            Toast.makeText(getContext(),"Information is not updated",Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });

        return view;
    }

}
