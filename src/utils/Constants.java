package utils;

public class Constants {

    public static class DIRECTIONS {
        public static final int LEFT =0;
        public static final int UP =1;
        public static final int RIGHT =2;
        public static final int DOWN =3;
    }


    public static class playerConstants{
        public static final int IDEAL = 0;
        public static final int RUN = 1;
        public static final int JUMP = 2;
        public static final int FALLING = 3;
        public static final int GROUND = 4;
        public static final int HIT = 5;
        public static final int ATTACK1 = 6;
        public static final int ATTACK_JUMP1 = 7;
        public static final int ATTACK_JUMP2 = 8;


        public static int getImagesAmount(int playerAction){
            switch(playerAction){
                case RUN :
                    return 6;
                case IDEAL:
                    return 5;
                case JUMP:
                case ATTACK1:
                case ATTACK_JUMP1:
                case ATTACK_JUMP2:
                    return 3;
                case HIT:
                    return 4;
                case GROUND:
                    return 2;
                case FALLING:
                default:
                    return 1;
            }
        }

    }
}
