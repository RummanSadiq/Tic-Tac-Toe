package sample;

import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;

public class Controller{

    public Label position1, position2, position3, position4, position5, position6, position7, position8, position9;
    public String you;
    public Label turnId;
    public Label youScore ;
    public Label computerScore;
    public Button refreshButton;
    public Boolean allowInput;
    public Shot[] s;


    public void initialize() {
        handleRefreshButton();
        s = new Shot[9];
        if(you.equalsIgnoreCase("second")) {
            computerInput();
        }
    }

    //mn ye kar raha hn k computer 1st time tu koi random shots khaly ga lekin agar wu un sy game har jata hai tu next time jab game
    //ki position same hoti hai tu wo apni last shot ku chage kary ga aur agar wo dubara har jata hai tu wo next time dubara apni
    //last shot ki position change kary ga. aur tab kar karta rahy ga jab tak namam possible positions visited nai ho jati.
    //jab tamam position try karny k bad bi wu nai jeetata tu wu apni 2nd last shot ki position ku change karna shuru kar dy ga.
    //ik dafa 2nd last shot ki position change karny k bad dubara last position k har possible position ku not-visited kar dy ga
    //aur un sub ku dubara try kary ga.

    //ya phir ye ho sakta hai k wu har shot pe board ki position dekhy aur wu shot choose kary jis ki flag ki value ziyada ho agar wu jeetjata hai tu

    //yar phir wu board ki position dekhkar wu shot ly ju box ya tu khali hai ya pehly sy visited nai hai agar wu harta hai tu sam ju sab sy pehly approach btai hai
    //uss sy wu last sy shuru kary ga aur shots ki positions change karna shuru kar de ga.


    //mery zehan ik idea aya hai. k computer apna koi bi action change na kary. balky ye dekhy kiya iss tarha ki situation pehly user k
    //samny ai hai agar ai hai tu uss ny uss situation mn kya kiya. agar user wu kar k jeeta tu wohi karo warna kuch aur. ab mujy ye samajh
    // nai a raha k kiya ye AI mn aye ga kyu k computer sirf tabi game jeet sakta hai jab iss tarha ki game pehly kheli hu.
    // koi nai situation a jae tu kiya wu purani situation sy kuch siky ga agar sikhy ga tu kessy.Basically can the computer predict
    // that if you were at his position what approach you would have done. if yes, how to implement it. if no, then try to implement
    // it and make sure.
    // ab iss tareqy sy agar solve karty hn tu hm har shot k bad board k har position ki values store nai kary gy balky ye store
    //gy k kn har same shot ka ik dursy sy kitna difference hai lekin iss tarha karty hn tu masla ho jae ga. tu behtar ye ho ga
    //shuru mn hm board ki har position ku store kare aur computer har dafar shot ye pehly check kary aur user ki copy kary agar
    //jeeta tha lekin agar wu hara tha ya iss tarha ki koi situation pehly nai ai tu sam same shots k darmiyan difference
    // check kary aur phir wu tamam situation dekhy jaha difference same hai tu same action choose kary. agar same nai hai tu kai
    //pe bi laga dy aur agar jeetata hai tu ussy store kar ny warna next time apna action change kary.

    //ya phir hm essa karty hn k har dafa jab boad ki position ku store kar rahy hn gy tu uss 4 tariq sysave karen gy yani k har side sy.
    // ya phir store ik hi side sy karen lekin jab computer ny guess karna hu tu us position ky 4 dafa rotate kar k uss ka match
    //dhonda jae.

    private void computerInput() {

    }

    public void handleRefreshButton() {

        reset();
        turnId.setStyle("-fx-background-color: #F5F5F5;");
        allowInput = true;
        if(Math.random()*100 > 50) {
            you = "second";
            turnId.setText("You go 2nd");
        }
        else {
            you = "first";
            turnId.setText("You go 1st");
        }
    }

    public void reset() {
        position1.setText("");
        position2.setText("");
        position3.setText("");
        position4.setText("");
        position5.setText("");
        position6.setText("");
        position7.setText("");
        position8.setText("");
        position9.setText("");
    }

    public void handleAction1(MouseEvent mouseEvent) {
        changeValue(position1);
    }

    public void handleAction9(MouseEvent mouseEvent) {
        changeValue(position9);
    }

    public void handleAction8(MouseEvent mouseEvent) {
        changeValue(position8);
    }

    public void handleAction7(MouseEvent mouseEvent) {
        changeValue(position7);
    }

    public void handleAction6(MouseEvent mouseEvent) {
        changeValue(position6);
    }

    public void handleAction5(MouseEvent mouseEvent) {
        changeValue(position5);
    }

    public void handleAction4(MouseEvent mouseEvent) {
        changeValue(position4);
    }

    public void handleAction3(MouseEvent mouseEvent) {
        changeValue(position3);
    }

    public void handleAction2(MouseEvent mouseEvent) {
        changeValue(position2);
    }

    public void changeValue(Label position) {
        if(allowInput) {
            if(position.getText() == "" ) {
                if(you.equalsIgnoreCase("first")) {
                    position.setText("X");
                    turnId.setText("You are 'X'");
                } else {
                    position.setText("O");
                    turnId.setText("You are 'O'");
                }
                checkWin();
            }
        }
    }

    public void checkWin() {
        if(checkPosition(position1, position2, position3)) {
                checkWhoWon(position1.getText());
        }
        else if(checkPosition(position4, position5, position6)) {
            checkWhoWon(position4.getText());
        }
        else if(checkPosition(position7, position8, position9)) {
            checkWhoWon(position7.getText());
        }
        else if(checkPosition(position1, position4, position7)) {
            checkWhoWon(position1.getText());
        }
        else if(checkPosition(position2, position5, position8)) {
            checkWhoWon(position2.getText());
        }
        else if(checkPosition(position3, position6, position9)) {
            checkWhoWon(position3.getText());
        }
        else if(checkPosition(position1, position5, position9)) {
            checkWhoWon(position1.getText());
        }
        else if(checkPosition(position3, position5, position7)) {
            checkWhoWon(position3.getText());
        }
    }

    public boolean checkPosition(Label p1, Label p2, Label p3) {
        if(p1.getText() != "" && p2.getText() != "" && p3.getText() != "" ) {
            if (p1.getText().charAt(0) == p2.getText().charAt(0) &&
                    p1.getText().charAt(0) == p3.getText().charAt(0)) {
                return true;
            }
        }
        return false;
    }

    public void checkWhoWon(String text) {
        int cur;
        if((text.charAt(0) == 'X' && you.equalsIgnoreCase("first")) || ((text.charAt(0) == 'O' && you.equalsIgnoreCase("second")))){
            turnId.setText("You Won!");
            cur = Integer.parseInt(youScore.getText());
            cur++;
            youScore.setText(Integer.toString(cur));
        } else {
            turnId.setText("Computer Won!");
            cur = Integer.parseInt(computerScore.getText());
            cur++;
            computerScore.setText(Integer.toString(cur));
        }
        turnId.setStyle("-fx-background-color: red;");
        allowInput = false;
    }
}
