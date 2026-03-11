package methodChaining;

public class Tour {

    public String place;

    public String modeOfTravel;

    public String getPlace() {
        return place;
    }

    public Tour setPlace(String place) {
        this.place = place;
        return this;
    }

    public String getModeOfTravel() {
        return modeOfTravel;
    }

    public Tour setModeOfTravel(String modeOfTravel) {
        this.modeOfTravel = modeOfTravel;
        return this;
    }

    public void heyWhereAreYouGoing(){
        System.out.println(getPlace());
        System.out.println("You guys going by " + getModeOfTravel() + " to " + getPlace() + "?");
    }

    public static void main(String[] args) {
        Tour tour = new Tour();
        tour.setPlace("Ella").setModeOfTravel("Car").heyWhereAreYouGoing();


    }
}
