package src;

import java.util.ArrayList;

class Characterified_image {
    private ArrayList<String> characterified_image;

    Characterified_image(ArrayList<String> characterified_image){
        this.characterified_image = characterified_image;
    }

    @Override
    public String toString() {
        String res = "";
        for(int x=0; x<characterified_image.size(); x++){
            if(x>0){
                res += " \n";
            }
            res += characterified_image.get(x);
        }
        return res;
    }
}