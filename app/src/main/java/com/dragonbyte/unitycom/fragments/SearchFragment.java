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
import android.widget.Toast;

import com.dragonbyte.unitycom.R;
import com.dragonbyte.unitycom.activities.SearchResults;

import java.util.ArrayList;
import java.util.List;

import static android.R.id.list;

/**
 * Created by Antonia on 4/17/2017.
 */

public class SearchFragment extends Fragment {

    int selected = 0;
    String product;
    Button button;
    CheckBox checkbox;

    View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.e("Here created", "OK");
        view = inflater.inflate(R.layout.search_list, container, false);
        button = (Button) view.findViewById(R.id.search);

        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                selected = 0;
                checkbox = (CheckBox) view.findViewById(R.id.Beans);
                if (checkbox.isChecked()) {
                    addToList("Beans");
                    selected ++;
                }
                checkbox = (CheckBox) view.findViewById(R.id.Beetroot);
                if (checkbox.isChecked()) {
                    addToList("Beetroot");
                    selected ++;
                }
                checkbox = (CheckBox) view.findViewById(R.id.Cabbage);
                if (checkbox.isChecked()) {
                    addToList("Cabbage");
                    selected ++;
                }
                checkbox = (CheckBox) view.findViewById(R.id.Carrots);
                if (checkbox.isChecked()) {
                    addToList("Carrots");
                    selected ++;
                }
                checkbox = (CheckBox) view.findViewById(R.id.Cauliflower);
                if (checkbox.isChecked()) {
                    addToList("Cauliflower");
                    selected ++;
                }
                checkbox = (CheckBox) view.findViewById(R.id.Celery);
                if (checkbox.isChecked()) {
                    addToList("Celery");
                    selected ++;
                }
                checkbox = (CheckBox) view.findViewById(R.id.Corn);
                if (checkbox.isChecked()) {
                    addToList("Corn");
                    selected ++;
                }
                checkbox = (CheckBox) view.findViewById(R.id.Cucumbers);
                if (checkbox.isChecked()) {
                    addToList("Cucumbers");
                    selected ++;
                }
                checkbox = (CheckBox) view.findViewById(R.id.Eggplant);
                if (checkbox.isChecked()) {
                    addToList("Eggplant");
                    selected ++;
                }
                checkbox = (CheckBox) view.findViewById(R.id.Garlic);
                if (checkbox.isChecked()) {
                    addToList("Garlic");
                    selected ++;
                }
                checkbox = (CheckBox) view.findViewById(R.id.Kohlrabi);
                if (checkbox.isChecked()) {
                    addToList("Kohlrabi");
                    selected ++;
                }
                checkbox = (CheckBox) view.findViewById(R.id.Leeks);
                if (checkbox.isChecked()) {
                    addToList("Leeks");
                    selected ++;
                }
                checkbox = (CheckBox) view.findViewById(R.id.Lettuce);
                if (checkbox.isChecked()) {
                    addToList("Lettuce");
                    selected ++;
                }
                checkbox = (CheckBox) view.findViewById(R.id.Mushrooms);
                if (checkbox.isChecked()) {
                    addToList("Mushrooms");
                    selected ++;
                }
                checkbox = (CheckBox) view.findViewById(R.id.Onions);
                if (checkbox.isChecked()) {
                    addToList("Onions");
                    selected ++;
                }
                checkbox = (CheckBox) view.findViewById(R.id.Parsnip);
                if (checkbox.isChecked()) {
                    addToList("Parsnip");
                    selected ++;
                }
                checkbox = (CheckBox) view.findViewById(R.id.Peas);
                if (checkbox.isChecked()) {
                    addToList("Peas");
                    selected ++;
                }
                checkbox = (CheckBox) view.findViewById(R.id.Peppers);
                if (checkbox.isChecked()) {
                    addToList("Peppers");
                    selected ++;
                }
                checkbox = (CheckBox) view.findViewById(R.id.Potatoes);
                if (checkbox.isChecked()) {
                    addToList("Potatoes");
                    selected ++;
                }
                checkbox = (CheckBox) view.findViewById(R.id.Pumpkins);
                if (checkbox.isChecked()) {
                    addToList("Pumpkins");
                    selected ++;
                }
                checkbox = (CheckBox) view.findViewById(R.id.Radishes);
                if (checkbox.isChecked()) {
                    addToList("Radishes");
                    selected ++;
                }
                checkbox = (CheckBox) view.findViewById(R.id.Spinach);
                if (checkbox.isChecked()) {
                    addToList("Spinach");
                    selected ++;
                }
                checkbox = (CheckBox) view.findViewById(R.id.Tomatoes);
                if (checkbox.isChecked()) {
                    addToList("Tomatoes");
                    selected ++;
                }
                checkbox = (CheckBox) view.findViewById(R.id.Apples);
                if (checkbox.isChecked()) {
                    addToList("Apples");
                    selected ++;
                }
                checkbox = (CheckBox) view.findViewById(R.id.Apricots);
                if (checkbox.isChecked()) {
                    addToList("Apricots");
                    selected ++;
                }
                checkbox = (CheckBox) view.findViewById(R.id.Blueberries);
                if (checkbox.isChecked()) {
                    addToList("Blueberries");
                    selected ++;
                }
                checkbox = (CheckBox) view.findViewById(R.id.Citrus);
                if (checkbox.isChecked()) {
                    addToList("Citrus");
                    selected ++;
                }
                checkbox = (CheckBox) view.findViewById(R.id.Cherries);
                if (checkbox.isChecked()) {
                    addToList("Cherries");
                    selected ++;
                }
                checkbox = (CheckBox) view.findViewById(R.id.Cranberries);
                if (checkbox.isChecked()) {
                    addToList("Cranberries");
                    selected ++;
                }
                checkbox = (CheckBox) view.findViewById(R.id.Grapes);
                if (checkbox.isChecked()) {
                    addToList("Grapes");
                    selected ++;
                }
                checkbox = (CheckBox) view.findViewById(R.id.Melon);
                if (checkbox.isChecked()) {
                    addToList("Melon");
                    selected ++;
                }
                checkbox = (CheckBox) view.findViewById(R.id.Nectarine);
                if (checkbox.isChecked()) {
                    addToList("Nectarine");
                    selected ++;
                }
                checkbox = (CheckBox) view.findViewById(R.id.Peaches);
                if (checkbox.isChecked()) {
                    addToList("Peaches");
                    selected ++;
                }
                checkbox = (CheckBox) view.findViewById(R.id.Pears);
                if (checkbox.isChecked()) {
                    addToList("Pears");
                    selected ++;
                }
                checkbox = (CheckBox) view.findViewById(R.id.Plums);
                if (checkbox.isChecked()) {
                    addToList("Plums");
                    selected ++;
                }
                checkbox = (CheckBox) view.findViewById(R.id.Raspberries);
                if (checkbox.isChecked()) {
                    addToList("Raspberries");
                    selected ++;
                }
                checkbox = (CheckBox) view.findViewById(R.id.Strawberries);
                if (checkbox.isChecked()) {
                    addToList("Strawberries");
                    selected ++;
                }
                checkbox = (CheckBox) view.findViewById(R.id.Watermelon);
                if (checkbox.isChecked()) {
                    addToList("Watermelon");
                    selected ++;
                }
                checkbox = (CheckBox) view.findViewById(R.id.Cans);
                if (checkbox.isChecked()) {
                    addToList("Cans");
                    selected ++;
                }
                checkbox = (CheckBox) view.findViewById(R.id.Honey);
                if (checkbox.isChecked()) {
                    addToList("Honey");
                    selected ++;
                }
                checkbox = (CheckBox) view.findViewById(R.id.Jam);
                if (checkbox.isChecked()) {
                    addToList("Jam");
                    selected ++;
                }
                checkbox = (CheckBox) view.findViewById(R.id.Pickles);
                if (checkbox.isChecked()) {
                    addToList("Pickles");
                    selected ++;
                }
                checkbox = (CheckBox) view.findViewById(R.id.Sauces);
                if (checkbox.isChecked()) {
                    addToList("Sauces");
                    selected ++;
                }
                checkbox = (CheckBox) view.findViewById(R.id.Zacusca);
                if (checkbox.isChecked()) {
                    addToList("Zacusca");
                    selected ++;
                }
                checkbox = (CheckBox) view.findViewById(R.id.Beef);
                if (checkbox.isChecked()) {
                    addToList("Beef");
                    selected ++;
                }
                checkbox = (CheckBox) view.findViewById(R.id.Chicken);
                if (checkbox.isChecked()) {
                    addToList("Chicken");
                    selected ++;
                }
                checkbox = (CheckBox) view.findViewById(R.id.Duck);
                if (checkbox.isChecked()) {
                    addToList("Duck");
                    selected ++;
                }
                checkbox = (CheckBox) view.findViewById(R.id.Fish);
                if (checkbox.isChecked()) {
                    addToList("Fish");
                    selected ++;
                }
                checkbox = (CheckBox) view.findViewById(R.id.Goat);
                if (checkbox.isChecked()) {
                    addToList("Goat");
                    selected ++;
                }
                checkbox = (CheckBox) view.findViewById(R.id.Goose);
                if (checkbox.isChecked()) {
                    addToList("Goose");
                    selected ++;
                }
                checkbox = (CheckBox) view.findViewById(R.id.Hen);
                if (checkbox.isChecked()) {
                    addToList("Hen");
                    selected ++;
                }
                checkbox = (CheckBox) view.findViewById(R.id.Lamb);
                if (checkbox.isChecked()) {
                    addToList("Lamb");
                    selected ++;
                }
                checkbox = (CheckBox) view.findViewById(R.id.Mutton);
                if (checkbox.isChecked()) {
                    addToList("Mutton");
                    selected ++;
                }
                checkbox = (CheckBox) view.findViewById(R.id.Pork);
                if (checkbox.isChecked()) {
                    addToList("Pork");
                    selected ++;
                }
                checkbox = (CheckBox) view.findViewById(R.id.Rabbit);
                if (checkbox.isChecked()) {
                    addToList("Rabbit");
                    selected ++;
                }
                checkbox = (CheckBox) view.findViewById(R.id.Turkey);
                if (checkbox.isChecked()) {
                    addToList("Turkey");
                    selected ++;
                }
                checkbox = (CheckBox) view.findViewById(R.id.Butter);
                if (checkbox.isChecked()) {
                    addToList("Butter");
                    selected ++;
                }
                checkbox = (CheckBox) view.findViewById(R.id.Cheese);
                if (checkbox.isChecked()) {
                    addToList("Cheese");
                    selected ++;
                }
                checkbox = (CheckBox) view.findViewById(R.id.Cream);
                if (checkbox.isChecked()) {
                    addToList("Cream");
                    selected ++;
                }
                checkbox = (CheckBox) view.findViewById(R.id.Eggs);
                if (checkbox.isChecked()) {
                    addToList("Eggs");
                    selected ++;
                }
                checkbox = (CheckBox) view.findViewById(R.id.Ham);
                if (checkbox.isChecked()) {
                    addToList("Ham");
                    selected ++;
                }
                checkbox = (CheckBox) view.findViewById(R.id.Milk);
                if (checkbox.isChecked()) {
                    addToList("Milk");
                    selected ++;
                }

                checkbox = (CheckBox) view.findViewById(R.id.Pate);
                if (checkbox.isChecked()) {
                    addToList("Pate");
                    selected ++;
                }
                checkbox = (CheckBox) view.findViewById(R.id.Sausages);
                if (checkbox.isChecked()) {
                    addToList("Sausages");
                    selected ++;
                }
                checkbox = (CheckBox) view.findViewById(R.id.Yogurt);
                if (checkbox.isChecked()) {
                    addToList("Yogurt");
                    selected ++;
                }


                if(selected > 1) {
                    Toast.makeText(getContext(), "Please select only one checkbox.", Toast.LENGTH_LONG).show();
                }
                else {
                    if(selected == 1) {
                        Toast.makeText(getContext(), "Please wait...", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(getContext(), SearchResults.class);
                        intent.putExtra("PRODUCT_TO_SEARCH", product);
                        startActivity(intent);
                    }
                    else Toast.makeText(getContext(), "Please select one checkbox.", Toast.LENGTH_LONG).show();

                }
            }
        });
        return view;
    }

    void addToList(String string) {
        product = string;
    }

    void startSearch() {

    }
}

