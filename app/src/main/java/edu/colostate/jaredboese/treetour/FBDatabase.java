package edu.colostate.jaredboese.treetour;

/**
 * Created by Jared Boese on 2/8/2018.
 */

public class FBDatabase {
    public static class TreeInfo{

        public String CommonName;
        public String ScienceName;
        public String Zone;
        public String Height;
        public String Crown;
        public String Color;
        public String FallColor;
        public String Fruit;


        public TreeInfo() {

        }
        public TreeInfo(String CommonName, String ScienceName, String Zone, String Height,
                        String Crown, String Color, String FallColor, String Fruit){
        this.CommonName = CommonName;
        this.ScienceName = ScienceName;
        this.Zone = Zone;
        this.Height = Height;
        this.Crown = Crown;
        this.Color = Color;
        this.FallColor = FallColor;
        this.Fruit = Fruit;

        }

    }

}
