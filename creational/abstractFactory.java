interface Button {
    void render();
}

class Webbutton implements Button {
    @Override
    public void render()
    {
        System.out.println("Web button is rendered");
    }
}

class phoneButton implements Button {
    @Override
    public void render()
    {
        System.out.println("Phone Button is Rendered");
    }
}

class ButtonFactory {
    public static Button createButton(String device){
        switch (device){ 
            case "WEB":
                return new Webbutton();
            case "ANDROID":
                return new phoneButton();
            default: return new Webbutton();
        }
    }
}
interface Dialog {
    void render();
}

class WebDialog implements Dialog {
    @Override
    public void render()
    {
        System.out.println("Web Dialog is rendered");
    }
}

class phoneDialog implements Dialog {
    @Override
    public void render()
    {
        System.out.println("Phone Dialog is Rendered");
    }
}

interface GUIInterface {
    Button createButton();
    Dialog createDialog();
}

class WebInterface implements GUIInterface {
    @Override
    public Button createButton() {
        return new Webbutton();
    }
    @Override
    public Dialog createDialog() {
        return new WebDialog();
    }
}

class PhoneInterface implements GUIInterface {
    @Override
    public Button createButton() {
        return new phoneButton();
    }
    @Override
    public Dialog createDialog() {
        return new phoneDialog();
    }
}

class DialogFactory {
    public static Dialog createDialog(String device){
        switch (device){ 
            case "WEB":
                return new WebDialog();
            case "ANDROID":
                return new phoneDialog();
            default: return new WebDialog();
        }
    }
}

public class abstractFactory {

    public static void main(String[] args){
        // without abstract factory we would have to maintain the instance of each related product
       Button button = ButtonFactory.createButton("WEB");
       Dialog dialog = DialogFactory.createDialog("WEB");
       button.render();
       dialog.render();

       // with abstract factory we would have to maintain the instance of each related product
       System.out.println();
       // 
       GUIInterface ui;
       String os = "WEB";
        if (os == "WEB")
            ui = new WebInterface();
        else
            ui = new PhoneInterface();
        ui.createButton().render();
        ui.createDialog().render();
    }
}
