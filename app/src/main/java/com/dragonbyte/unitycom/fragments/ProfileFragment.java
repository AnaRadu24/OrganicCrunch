package com.dragonbyte.unitycom.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.dragonbyte.unitycom.Product;
import com.dragonbyte.unitycom.activities.UnityComMainActivity;
import com.dragonbyte.unitycom.R;
import com.dragonbyte.unitycom.User;
import com.google.android.gms.maps.model.LatLng;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlacePicker;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

import static android.app.Activity.RESULT_OK;

/**
 * Created by Antonia on 4/17/2017.
 */

public class ProfileFragment extends Fragment {
    
    View view;
    private static final int PLACE_PICKER_REQUEST = 1;
    EditText getFirstName, getFamilyName, getTelephone, getInfo;
    Button updateLocation, update;
    TextView name, contact, address, infoProducts;
    User user;
    CheckBox checkbox;
    String profileName, profileAddress, profileTelephone, profileProducts;

    String products = "";

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        Log.e("Here created", "OK");
        view = inflater.inflate(R.layout.profile_fragment, container, false);

        user = UnityComMainActivity.user;

        showInformation();
        show();

        return view;
    }
    
    void showInformation() {

        Log.e("Here", "Tries to update profile");

        name = (TextView) view.findViewById(R.id.FarmerName);
        contact = (TextView) view.findViewById(R.id.FarmerTelephone);
        address = (TextView) view.findViewById(R.id.FarmAddress);
        infoProducts = (TextView) view.findViewById(R.id.products);

        profileName = UnityComMainActivity.profileName;
        profileAddress = UnityComMainActivity.profileAddress;
        profileTelephone = UnityComMainActivity.profileTelephone;
        profileProducts = UnityComMainActivity.listProducts;

        name.setText(profileName);
        address.setText(profileAddress);
        contact.setText(profileTelephone);
        infoProducts.setText(profileProducts);


    }

    void show() {

        getFirstName = (EditText) view.findViewById(R.id.getFirstName);
        getFamilyName = (EditText) view.findViewById(R.id.getFamilyName);
        getTelephone = (EditText) view.findViewById(R.id.getTelephone);
        getInfo = (EditText) view.findViewById(R.id.getInfo);

        update = (Button) view.findViewById(R.id.update);
        updateLocation = (Button) view.findViewById(R.id.updateLocation);

        getFirstName.setText(user.getFirstName());
        getFamilyName.setText(user.getSecondName());
        getTelephone.setText(user.getTelephone());
        getInfo.setText(user.getAddress());
        //here goes everything
        
        
        updateLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                PlacePicker.IntentBuilder intentBuilder = new PlacePicker.IntentBuilder();
                Intent intent = null;
                try {
                    intent = intentBuilder.build(getActivity());
                } catch (GooglePlayServicesRepairableException e) {
                    e.printStackTrace();
                    Log.e("In MAP", "Error!");
                } catch (GooglePlayServicesNotAvailableException e) {
                    Log.e("In MAP", "Error!");
                    e.printStackTrace();
                }
                // Start the Intent by requesting a result, identified by a request code.
               startActivityForResult(intent, PLACE_PICKER_REQUEST);

            }
        });
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                removeAllOccurences();

                checkbox = (CheckBox) view.findViewById(R.id.Beans);
                if (checkbox.isChecked()) {
                    addToList("Beans");
                }
                checkbox = (CheckBox) view.findViewById(R.id.Beetroot);
                if (checkbox.isChecked()) {
                    addToList("Beetroot");
                }
                checkbox = (CheckBox) view.findViewById(R.id.Cabbage);
                if (checkbox.isChecked()) {
                    addToList("Cabbage");
                }
                checkbox = (CheckBox) view.findViewById(R.id.Carrots);
                if (checkbox.isChecked()) {
                    addToList("Carrots");
                }
                checkbox = (CheckBox) view.findViewById(R.id.Cauliflower);
                if (checkbox.isChecked()) {
                    addToList("Cauliflower");
                }
                checkbox = (CheckBox) view.findViewById(R.id.Celery);
                if (checkbox.isChecked()) {
                    addToList("Celery");
                }
                checkbox = (CheckBox) view.findViewById(R.id.Corn);
                if (checkbox.isChecked()) {
                    addToList("Corn");
                }
                checkbox = (CheckBox) view.findViewById(R.id.Cucumbers);
                if (checkbox.isChecked()) {
                    addToList("Cucumbers");
                }
                checkbox = (CheckBox) view.findViewById(R.id.Eggplant);
                if (checkbox.isChecked()) {
                    addToList("Eggplant");
                }
                checkbox = (CheckBox) view.findViewById(R.id.Garlic);
                if (checkbox.isChecked()) {
                    addToList("Garlic");
                }
                checkbox = (CheckBox) view.findViewById(R.id.Kohlrabi);
                if (checkbox.isChecked()) {
                    addToList("Kohlrabi");
                }
                checkbox = (CheckBox) view.findViewById(R.id.Leeks);
                if (checkbox.isChecked()) {
                    addToList("Leeks");
                }
                checkbox = (CheckBox) view.findViewById(R.id.Lettuce);
                if (checkbox.isChecked()) {
                    addToList("Lettuce");
                }
                checkbox = (CheckBox) view.findViewById(R.id.Mushrooms);
                if (checkbox.isChecked()) {
                    addToList("Mushrooms");
                }
                checkbox = (CheckBox) view.findViewById(R.id.Onions);
                if (checkbox.isChecked()) {
                    addToList("Onions");
                }
                checkbox = (CheckBox) view.findViewById(R.id.Parsnip);
                if (checkbox.isChecked()) {
                    addToList("Parsnip");
                }
                checkbox = (CheckBox) view.findViewById(R.id.Peas);
                if (checkbox.isChecked()) {
                    addToList("Peas");
                }
                checkbox = (CheckBox) view.findViewById(R.id.Peppers);
                if (checkbox.isChecked()) {
                    addToList("Peppers");
                }
                checkbox = (CheckBox) view.findViewById(R.id.Potatoes);
                if (checkbox.isChecked()) {
                    addToList("Potatoes");
                }
                checkbox = (CheckBox) view.findViewById(R.id.Pumpkins);
                if (checkbox.isChecked()) {
                    addToList("Pumpkins");
                }
                checkbox = (CheckBox) view.findViewById(R.id.Radishes);
                if (checkbox.isChecked()) {
                    addToList("Radishes");
                }
                checkbox = (CheckBox) view.findViewById(R.id.Spinach);
                if (checkbox.isChecked()) {
                    addToList("Spinach");
                }
                checkbox = (CheckBox) view.findViewById(R.id.Tomatoes);
                if (checkbox.isChecked()) {
                    addToList("Tomatoes");
                }
                checkbox = (CheckBox) view.findViewById(R.id.Apples);
                if (checkbox.isChecked()) {
                    addToList("Apples");
                }
                checkbox = (CheckBox) view.findViewById(R.id.Apricots);
                if (checkbox.isChecked()) {
                    addToList("Apricots");
                }
                checkbox = (CheckBox) view.findViewById(R.id.Blueberries);
                if (checkbox.isChecked()) {
                    addToList("Blueberries");
                }
                checkbox = (CheckBox) view.findViewById(R.id.Citrus);
                if (checkbox.isChecked()) {
                    addToList("Citrus");
                }
                checkbox = (CheckBox) view.findViewById(R.id.Cherries);
                if (checkbox.isChecked()) {
                    addToList("Cherries");
                }
                checkbox = (CheckBox) view.findViewById(R.id.Cranberries);
                if (checkbox.isChecked()) {
                    addToList("Cranberries");
                }
                checkbox = (CheckBox) view.findViewById(R.id.Grapes);
                if (checkbox.isChecked()) {
                    addToList("Grapes");
                }
                checkbox = (CheckBox) view.findViewById(R.id.Melon);
                if (checkbox.isChecked()) {
                    addToList("Melon");
                }
                checkbox = (CheckBox) view.findViewById(R.id.Nectarine);
                if (checkbox.isChecked()) {
                    addToList("Nectarine");
                }
                checkbox = (CheckBox) view.findViewById(R.id.Peaches);
                if (checkbox.isChecked()) {
                    addToList("Peaches");
                }
                checkbox = (CheckBox) view.findViewById(R.id.Pears);
                if (checkbox.isChecked()) {
                    addToList("Pears");
                }
                checkbox = (CheckBox) view.findViewById(R.id.Plums);
                if (checkbox.isChecked()) {
                    addToList("Plums");
                }
                checkbox = (CheckBox) view.findViewById(R.id.Raspberries);
                if (checkbox.isChecked()) {
                    addToList("Raspberries");
                }
                checkbox = (CheckBox) view.findViewById(R.id.Strawberries);
                if (checkbox.isChecked()) {
                    addToList("Strawberries");
                }
                checkbox = (CheckBox) view.findViewById(R.id.Watermelon);
                if (checkbox.isChecked()) {
                    addToList("Watermelon");
                }
                checkbox = (CheckBox) view.findViewById(R.id.Cans);
                if (checkbox.isChecked()) {
                    addToList("Cans");
                }
                checkbox = (CheckBox) view.findViewById(R.id.Honey);
                if (checkbox.isChecked()) {
                    addToList("Honey");
                }
                checkbox = (CheckBox) view.findViewById(R.id.Jam);
                if (checkbox.isChecked()) {
                    addToList("Jam");
                }
                checkbox = (CheckBox) view.findViewById(R.id.Pickles);
                if (checkbox.isChecked()) {
                    addToList("Pickles");
                }
                checkbox = (CheckBox) view.findViewById(R.id.Sauces);
                if (checkbox.isChecked()) {
                    addToList("Sauces");
                }
                checkbox = (CheckBox) view.findViewById(R.id.Zacusca);
                if (checkbox.isChecked()) {
                    addToList("Zacusca");
                }
                checkbox = (CheckBox) view.findViewById(R.id.Beef);
                if (checkbox.isChecked()) {
                    addToList("Beef");
                }
                checkbox = (CheckBox) view.findViewById(R.id.Chicken);
                if (checkbox.isChecked()) {
                    addToList("Chicken");
                }
                checkbox = (CheckBox) view.findViewById(R.id.Duck);
                if (checkbox.isChecked()) {
                    addToList("Duck");
                }
                checkbox = (CheckBox) view.findViewById(R.id.Fish);
                if (checkbox.isChecked()) {
                    addToList("Fish");
                }
                checkbox = (CheckBox) view.findViewById(R.id.Goat);
                if (checkbox.isChecked()) {
                    addToList("Goat");
                }
                checkbox = (CheckBox) view.findViewById(R.id.Goose);
                if (checkbox.isChecked()) {
                    addToList("Goose");
                }
                checkbox = (CheckBox) view.findViewById(R.id.Hen);
                if (checkbox.isChecked()) {
                    addToList("Hen");
                }
                checkbox = (CheckBox) view.findViewById(R.id.Lamb);
                if (checkbox.isChecked()) {
                    addToList("Lamb");
                }
                checkbox = (CheckBox) view.findViewById(R.id.Mutton);
                if (checkbox.isChecked()) {
                    addToList("Mutton");
                }
                checkbox = (CheckBox) view.findViewById(R.id.Pork);
                if (checkbox.isChecked()) {
                    addToList("Pork");
                }
                checkbox = (CheckBox) view.findViewById(R.id.Rabbit);
                if (checkbox.isChecked()) {
                    addToList("Rabbit");
                }
                checkbox = (CheckBox) view.findViewById(R.id.Turkey);
                if (checkbox.isChecked()) {
                    addToList("Turkey");
                }
                checkbox = (CheckBox) view.findViewById(R.id.Butter);
                if (checkbox.isChecked()) {
                    addToList("Butter");
                }
                checkbox = (CheckBox) view.findViewById(R.id.Cheese);
                if (checkbox.isChecked()) {
                    addToList("Cheese");
                }
                checkbox = (CheckBox) view.findViewById(R.id.Cream);
                if (checkbox.isChecked()) {
                    addToList("Cream");
                }
                checkbox = (CheckBox) view.findViewById(R.id.Eggs);
                if (checkbox.isChecked()) {
                    addToList("Eggs");
                }
                checkbox = (CheckBox) view.findViewById(R.id.Ham);
                if (checkbox.isChecked()) {
                    addToList("Ham");
                }
                checkbox = (CheckBox) view.findViewById(R.id.Milk);
                if (checkbox.isChecked()) {
                    addToList("Milk");
                }

                checkbox = (CheckBox) view.findViewById(R.id.Pate);
                if (checkbox.isChecked()) {
                    addToList("Pate");
                }
                checkbox = (CheckBox) view.findViewById(R.id.Sausages);
                if (checkbox.isChecked()) {
                    addToList("Sausages");
                }
                checkbox = (CheckBox) view.findViewById(R.id.Yogurt);
                if (checkbox.isChecked()) {
                    addToList("Yogurt");
                }

                Log.e("In profile fragment", "Trying to update...");

                user.setFirstName(getFirstName.getText().toString());
                user.setSecondName(getFamilyName.getText().toString());
                user.setAddress(getInfo.getText().toString());
                user.setTelephone(getTelephone.getText().toString());

                if(products.endsWith(", "))
                    products = products.substring(0, products.length()-2);

                user.setListOfProducts(products);

                FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();
                DatabaseReference mDatabase;

                Toast.makeText(getContext(), "Information updated!", Toast.LENGTH_LONG).show();

                mDatabase = FirebaseDatabase.getInstance().getReference();
                mDatabase.child("users").child(currentUser.getUid()).setValue(user);

                
            }
        });

    }

    void removeFromList(String product) {
        FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();
        DatabaseReference mDatabase;

        mDatabase = FirebaseDatabase.getInstance().getReference();
        mDatabase.child("products").child(product).child(currentUser.getUid()).removeValue();
    }

    void addToList(String product) {
        products = products + product + ", ";

        FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();
        DatabaseReference mDatabase;

        mDatabase = FirebaseDatabase.getInstance().getReference();

        Product prod = new Product(
                user.getEmail(),
                getFirstName.getText().toString() + " " + getFamilyName.getText().toString(),
                getInfo.getText().toString(),
                getTelephone.getText().toString()
                );
        mDatabase.child("products").child(product).child(currentUser.getUid()).setValue(prod);
    }
    @Override
    //TODO: Maybe protected? See if crashes
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == PLACE_PICKER_REQUEST) {
            if (resultCode == RESULT_OK) {
                Place place = PlacePicker.getPlace(getActivity(), data);
                String toastMsg = String.format("Place: %s", place.getName());
                Toast.makeText(getActivity(), toastMsg, Toast.LENGTH_LONG).show();

                LatLng location = place.getLatLng();

                user.setLatitude(location.latitude);
                user.setLongitude(location.longitude);

            }
        }
    }

    void removeAllOccurences() {
        removeFromList("Beans");
        removeFromList("Beetroot");
        removeFromList("Cabbage");
        removeFromList("Cauliflower");
        removeFromList("Celery");
        removeFromList("Corn");
        removeFromList("Cucumbers");
        removeFromList("Eggplant");
        removeFromList("Garlic");
        removeFromList("Kohlrabi");
        removeFromList("Leeks");
        removeFromList("Lettuce");
        removeFromList("Mushrooms");
        removeFromList("Onions");
        removeFromList("Parsnip");
        removeFromList("Peas");
        removeFromList("Peppers");
        removeFromList("Potatoes");
        removeFromList("Pumpkins");
        removeFromList("Radishes");
        removeFromList("Spinach");
        removeFromList("Tomatoes");
        removeFromList("Apples");
        removeFromList("Apricots");
        removeFromList("Blueberries");
        removeFromList("Citrus");
        removeFromList("Cherries");
        removeFromList("Cranberries");
        removeFromList("Grapes");
        removeFromList("Melon");
        removeFromList("Nectarine");
        removeFromList("Peaches");
        removeFromList("Pears");
        removeFromList("Raspberries");
        removeFromList("Strawberries");
        removeFromList("Watermelon");
        removeFromList("Cans");
        removeFromList("Honey");
        removeFromList("Jam");
        removeFromList("Pickles");
        removeFromList("Sauces");
        removeFromList("Zacusca");
        removeFromList("Beef");
        removeFromList("Chicken");
        removeFromList("Duck");
        removeFromList("Fish");
        removeFromList("Goat");
        removeFromList("Goose");
        removeFromList("Hen");
        removeFromList("Lamb");
        removeFromList("Turkey");
        removeFromList("Butter");
        removeFromList("Cheese");
        removeFromList("Cream");
        removeFromList("Eggs");
        removeFromList("Ham");
        removeFromList("Milk");
        removeFromList("Pate");
        removeFromList("Sausages");
        removeFromList("Yogurt");
    }
}
