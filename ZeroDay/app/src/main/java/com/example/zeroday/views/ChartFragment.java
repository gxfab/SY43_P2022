package com.example.zeroday.views;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import com.example.zeroday.R;
import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.charts.RadarChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.data.RadarData;
import com.github.mikephil.charting.data.RadarDataSet;
import com.github.mikephil.charting.data.RadarEntry;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.github.mikephil.charting.formatter.PercentFormatter;

import java.util.ArrayList;

/** Fragment contenant la page d'accueil ( t compris les deux graphiques ).
 * @author Zero Day
 * @version 1.0
 */
public class ChartFragment extends Fragment {

    private PieChart pieChart;
    private RadarChart radarChart;

    /** Fonction appelée à la création du fragment.
     * @param inflater
     * @param container Conteneur du fragment.
     * @param savedInstanceState
     * @return Le fragment
     */
        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_chart, container, false);

        //Recuperation du PieChart
        pieChart = view.findViewById(R.id.piechart);
        //Configuration du PieChart
        configPieChart();
        //Chargement du PieChart
        loadPieChart();

        //Recuperation du RadarChart
        radarChart = view.findViewById(R.id.radarchart);
        //Configuration du RadarChart
        configRadarChart();
        //Chargement du RadarChart
        loadRadarChart();


        return view;
    }

    /** Fonction de configuration du PieChart.
     */
    private void configPieChart(){
        //Trou central
        pieChart.setDrawHoleEnabled(true);
        //Mode pourcentages
        pieChart.setUsePercentValues(true);
        //Taille des labels des entrées
        pieChart.setEntryLabelTextSize(12);
        //Couleur des labels
        pieChart.setEntryLabelColor(Color.WHITE);
        //Désactivation de la description
        pieChart.getDescription().setEnabled(false);
        //Désactivation de la légende
        pieChart.getLegend().setEnabled(false);
        //Blocage du PieChart
        pieChart.setRotationEnabled(false);
    }

    /** Fonction de configuration du RadarChart.
     */
    private void configRadarChart(){
        //Blocage du RadarChart
        radarChart.setRotationEnabled(false);
        //Désactivation de la description
        radarChart.getDescription().setEnabled(false);
    }

    /** Fonction de chargement et affichage du RadarChart.
     */
    private void loadRadarChart(){
        ArrayList<RadarEntry> currentDataVals = new ArrayList<>();
        currentDataVals.add(new RadarEntry(3));
        currentDataVals.add(new RadarEntry(7));
        currentDataVals.add(new RadarEntry(2));
        currentDataVals.add(new RadarEntry(5));
        currentDataVals.add(new RadarEntry(3));
        currentDataVals.add(new RadarEntry(1));

        ArrayList<RadarEntry> lastDataVals = new ArrayList<>();
        lastDataVals.add(new RadarEntry(2));
        lastDataVals.add(new RadarEntry(6));
        lastDataVals.add(new RadarEntry(3));
        lastDataVals.add(new RadarEntry(4));
        lastDataVals.add(new RadarEntry(6));
        lastDataVals.add(new RadarEntry(4));

        //Création du premier dataset à partir des entrées ( dépenses actuelles )
        RadarDataSet currentDataSet = new RadarDataSet(currentDataVals, "Current expenses");

        //Création du second dataset à partir des entrées ( dépenses dernier cycle )
        RadarDataSet lastDataSet = new RadarDataSet(lastDataVals, "Last cycle expenses");

        //Ajout des couleurs
        currentDataSet.setFillColor(ContextCompat.getColor(getContext(), R.color.zero_purple));
        currentDataSet.setColor(ContextCompat.getColor(getContext(), R.color.zero_purple));
        currentDataSet.setDrawFilled(true);
        lastDataSet.setFillColor(ContextCompat.getColor(getContext(), R.color.zero_pink0));
        lastDataSet.setColor(ContextCompat.getColor(getContext(), R.color.zero_pink0));
        lastDataSet.setDrawFilled(true);

        //Préparation des données
        RadarData data = new RadarData();
        data.addDataSet(currentDataSet);
        data.addDataSet(lastDataSet);

        //Labels des données
        String[] labels = {"Hobbies","Health","Transports","Food","House","Custom"};
        XAxis xAxis = radarChart.getXAxis();
        //Ajout des labels
        xAxis.setValueFormatter(new IndexAxisValueFormatter(labels));

        //Ajout des données au RadarChart
        radarChart.setData(data);
        //Mise à jour du RadarChart
        radarChart.invalidate();
        //Animation du Radachart
        radarChart.animateX(800, Easing.EaseInOutQuad);
        radarChart.animateY(800, Easing.EaseInOutQuad);


    }




    private void loadPieChart(){

        ArrayList<PieEntry> entries = new ArrayList<>();
        entries.add(new PieEntry(0.2f, "Hobbies"));
        entries.add(new PieEntry(0.1f, "Health"));
        entries.add(new PieEntry(0.1f, "Transports"));
        entries.add(new PieEntry(0.05f, "Food"));
        entries.add(new PieEntry(0.15f, "House"));
        entries.add(new PieEntry(0.4f, "Custom"));


        final int[] MY_COLORS = {ContextCompat.getColor(getContext(), R.color.hobbies_color)
                ,ContextCompat.getColor(getContext(), R.color.health_color)
                ,ContextCompat.getColor(getContext(), R.color.transports_color)
                ,ContextCompat.getColor(getContext(), R.color.food_color)
                ,ContextCompat.getColor(getContext(), R.color.house_color)
                ,ContextCompat.getColor(getContext(), R.color.custom_expense_color)};

        //Préparation des couleurs
        ArrayList<Integer> colors = new ArrayList<>();
        for(int c: MY_COLORS) colors.add(c);

        //Création du dataset
        PieDataSet pieDataSet = new PieDataSet(entries, "Current expenses");

        //Ajout des couleurs au dataset
        pieDataSet.setColors(colors);

        PieData data = new PieData(pieDataSet);
        data.setDrawValues(true);
        data.setValueFormatter(new PercentFormatter(pieChart));
        data.setValueTextSize(12f);
        data.setValueTextColor(Color.WHITE);

        //Ajout des données au PieChart
        pieChart.setData(data);
        //Mise à jour du PieChart
        pieChart.invalidate();
        //Animation PieChart
        pieChart.animateY(800, Easing.EaseInOutQuad);

    }
}
