package com.oconte.david.mynews.Utils;

public class ConfigureNbPage {

    public static int getNumberPages(int totalNumberArticles) {

        Double numberPagesDouble = totalNumberArticles*0.1;
        int numberPages = numberPagesDouble.intValue();
        int rest = totalNumberArticles % 10;
        if (rest > 0){
            numberPages += 1;
        }
        return numberPages;

    }
}
