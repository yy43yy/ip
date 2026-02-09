public class Events extends Task{
    protected String from;
    protected String to;
    public Events(String description,String from, String to){
        super(description);
        this.from=from;
        this.to=to;
    }
    public String getFrom(){
        return from;
    }
    public String getTo(){
        return to;
    }
    public void setFrom(String from){
        this.from= from;
    }
    public void setTo(String to){
        this.to= to;
    }

    public String toString(){
        return "[E]"+super.toString()+"(from:" +getFrom()+"to:"+ getTo()+")";
    }
}
