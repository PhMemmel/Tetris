public class mainClass
{
    public static void main(String[] args)
    {
        Model model = new Model();
        View view = new View(model);
        Controller controller = new Controller(view, model);
        SoundPlayer soundPlayer = new SoundPlayer(model);
        model.addListener(soundPlayer);
    }
}
