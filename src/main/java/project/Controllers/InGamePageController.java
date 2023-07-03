package project.Controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import project.OpenNewWindow;
import project.Start;

import java.io.*;
import java.util.ArrayList;
import java.util.Objects;

public class InGamePageController {

    @FXML
    private TextField betField;

    @FXML
    private Label betLabel;

    @FXML
    private Label dealerScoreLabel;

    @FXML
    private Button doubleDownButton;

    @FXML
    private ImageView eightDealerCard;

    @FXML
    private ImageView eightPlayerCard;

    @FXML
    private ImageView eleventhDealerCard;

    @FXML
    private ImageView eleventhPlayerCard;

    @FXML
    private Label failLabel;

    @FXML
    private ImageView fifthDealerCard;

    @FXML
    private ImageView fifthPlayerCard;

    @FXML
    private ImageView firstDealerCard;

    @FXML
    private ImageView firstPlayerCard;

    @FXML
    private ImageView fourthDealerCard;

    @FXML
    private ImageView fourthPlayerCard;

    @FXML
    private Button hitButton;

    @FXML
    private Label informationLabel;

    @FXML
    private Button makeBetButton;

    @FXML
    private Label moneyLabel;

    @FXML
    private Button nextGameButton;

    @FXML
    private ImageView ninthDealerCard;

    @FXML
    private ImageView ninthPlayerCard;

    @FXML
    private Button outTheGameButton;

    @FXML
    private Label scoreLabel;

    @FXML
    private ImageView secondDealerCard;

    @FXML
    private ImageView secondPlayerCard;

    @FXML
    private ImageView seventhDealerCard;

    @FXML
    private ImageView seventhPlayerCard;

    @FXML
    private ImageView sixthDealerCard;

    @FXML
    private ImageView sixthPlayerCard;

    @FXML
    private Button standButton;

    @FXML
    private ImageView tenthDealerCard;

    @FXML
    private ImageView tenthPlayerCard;

    @FXML
    private ImageView thridDealerCard;

    @FXML
    private ImageView thridPlayerCard;

    private final String[] suitsList = new String[]{"Clubs", "Diamonds", "Hearts", "Spades"};
    static final String[] cardsList = new String[]{"two", "three", "four", "five", "six", "seven", "eight", "nine", "ten", "jack", "queen", "king", "ace"};
    static final int[] cardsScore = new int[]{2, 3, 4, 5, 6, 7, 8, 9, 10, 10, 10, 10, 11};

    int dealerAce = 0;
    int userAce = 0;


    ArrayList<String> allCardsInGame = new ArrayList<>();

    @FXML
    void initialize() {
        setMoneyOnLabel();

        outTheGameButton.setOnAction(actionEvent -> {
            OpenNewWindow.openNewWindow(outTheGameButton,"firstGamePage");
        });
        betField.setOnMouseClicked(mouseEvent -> {
            betField.setStyle("-fx-background-color: #fafafa; -fx-background-radius: 10; -fx-border-color: silver; -fx-border-radius: 10;");
        });
        nextGameButton.setOnAction(actionEvent -> {
            informationLabel.setText("");
            failLabel.setText("");

            hitButton.setDisable(true);
            standButton.setDisable(true);
            doubleDownButton.setDisable(true);
            makeBetButton.setDisable(false);

            nextGameButton.setDisable(true);
            nextGameButton.setStyle("-fx-background-color: red; -fx-background-radius: 10;");

            scoreLabel.setText("0");
            betLabel.setText("0");
            betField.setText("0");
            betField.setEditable(true);
            dealerScoreLabel.setText("0");

            if (firstDealerCard.getImage() != null) {
                firstDealerCard.setImage(null);

            } if (secondDealerCard.getImage() != null) {
                secondDealerCard.setImage(null);

            } if (thridDealerCard.getImage() != null) {
                thridDealerCard.setImage(null);

            }if (fourthDealerCard.getImage() != null) {
                fourthDealerCard.setImage(null);

            } if (fifthDealerCard.getImage() != null) {
                fifthDealerCard.setImage(null);

            } if (sixthDealerCard.getImage() != null) {
                sixthDealerCard.setImage(null);

            } if (seventhDealerCard.getImage() != null) {
                seventhDealerCard.setImage(null);

            } if (eightDealerCard.getImage() != null) {
                eightDealerCard.setImage(null);

            } if (ninthDealerCard.getImage() != null) {
                ninthDealerCard.setImage(null);

            } if (tenthDealerCard.getImage() != null) {
                tenthDealerCard.setImage(null);

            } if (eleventhDealerCard.getImage() != null) {
                eleventhDealerCard.setImage(null);
            }

            if (firstPlayerCard.getImage() != null) {
                firstPlayerCard.setImage(null);

            }if (secondPlayerCard.getImage() != null) {
                secondPlayerCard.setImage(null);

            } if (thridPlayerCard.getImage() != null) {
                thridPlayerCard.setImage(null);

            } if (fourthPlayerCard.getImage() != null) {
                fourthPlayerCard.setImage(null);

            } if (fifthPlayerCard.getImage() != null) {
                fifthPlayerCard.setImage(null);

            } if (sixthPlayerCard.getImage() != null) {
                sixthPlayerCard.setImage(null);

            } if (seventhPlayerCard.getImage() != null) {
                seventhPlayerCard.setImage(null);

            } if (eightPlayerCard.getImage() != null) {
                eightPlayerCard.setImage(null);

            } if (ninthPlayerCard.getImage() != null) {
                ninthPlayerCard.setImage(null);

            } if (tenthPlayerCard.getImage() != null) {
                tenthPlayerCard.setImage(null);

            }if (eleventhPlayerCard.getImage() != null) {
                eleventhPlayerCard.setImage(null);
            }
        });

        makeBetButton.setOnAction(actionEvent -> {

            allCardsInGame.clear();

            String tryThisBet = betField.getText();

            if (moneyLabel.getText().equals("∞")) {
                betLabel.setText(tryThisBet);

                betField.setEditable(false);

                hitButton.setDisable(false);
                standButton.setDisable(false);
                doubleDownButton.setDisable(false);
                makeBetButton.setDisable(true);

                Class<?> clazz = getClass();
                String makeFistDealerCard = Objects.requireNonNull(clazz.getResource("/project/Assets/shirt_card.jpg")).toExternalForm();
                Image image = new Image(makeFistDealerCard);
                firstDealerCard.setImage(image);

                makeDealerCard();

                while (!makePlayerCard());
                while (!makePlayerCard());

                return;
            }


            int money = Integer.parseInt(moneyLabel.getText());

            if (firstDealerCard.getImage() != null) {
                makeBetButton.setStyle("-fx-background-color: #bf9b28; -fx-background-radius: 10; -fx-border-color: red; -fx-border-radius: 10;");
                return;
            }


            try {
                int bet = Integer.parseInt(tryThisBet);
                if (bet < 10) {
                    informationLabel.setText("");
                    failLabel.setText("The bet cannot be less than 10");
                    return;
                } else if (!(bet <= money)) {
                    informationLabel.setText("");
                    failLabel.setText("The bet cannot be more than your amount of money");
                    return;
                } else {
                    betLabel.setText(String.valueOf(bet));
                }
            } catch (Exception e) {
                betField.setStyle("-fx-background-color: #fafafa; -fx-background-radius: 10; -fx-border-color: red; -fx-border-radius: 10;");
                return;
            }

            betField.setEditable(false);
            setMoneyOnAccountFile(Integer.parseInt(moneyLabel.getText())-Integer.parseInt(betLabel.getText()));
            moneyLabel.setText(String.valueOf(Integer.parseInt(moneyLabel.getText())-Integer.parseInt(betLabel.getText())));

            hitButton.setDisable(false);
            standButton.setDisable(false);
            doubleDownButton.setDisable(false);
            makeBetButton.setDisable(true);

            Class<?> clazz = getClass();
            String makeFistDealerCard = Objects.requireNonNull(clazz.getResource("/project/Assets/shirt_card.jpg")).toExternalForm();
            Image image = new Image(makeFistDealerCard);
            firstDealerCard.setImage(image);

            makeDealerCard();
            while (!makePlayerCard());
            while (!makePlayerCard());
        });

        hitButton.setOnAction(actionEvent -> {
            while (!makePlayerCard());
        });

        doubleDownButton.setOnAction(actionEvent -> {
            while (!makePlayerCard());
            if (moneyLabel.getText().equals("∞")) {
                standVoid();
                return;
            }

            moneyLabel.setText(String.valueOf(Integer.parseInt(moneyLabel.getText()) - Integer.parseInt(betLabel.getText())));
            betLabel.setText(String.valueOf(Integer.parseInt(betLabel.getText()) * 2));
            standVoid();
        });

        standButton.setOnAction(actionEvent -> {
            standVoid();
        });
    }

    public void standVoid() {
        firstDealerCard.setImage(null);

        while (Integer.parseInt(dealerScoreLabel.getText()) < 17 && Integer.parseInt(dealerScoreLabel.getText()) < Integer.parseInt(scoreLabel.getText())) {
            makeDealerCard();
        }
        hitButton.setDisable(false);
        standButton.setDisable(false);
        doubleDownButton.setDisable(false);
        finishGame();
    }

    private void finishGame() {
        informationLabel.setText("");
        failLabel.setText("");

        int userScore = Integer.parseInt(scoreLabel.getText());
        int dealerScore = Integer.parseInt(dealerScoreLabel.getText());

        if (moneyLabel.getText().equals("∞")) {
            if (userScore == 21) {
                informationLabel.setText("You have won!");

            } else if (userScore > dealerScore && userScore < 21) {
                informationLabel.setText("You have won! You have more points than the dealer!");

            } else if (dealerScore > 21) {
                informationLabel.setText("You have won! Dealer got over it!");

            } else if (userScore == dealerScore) {
                informationLabel.setText("Draw!");

            } else if (userScore < dealerScore) {
                failLabel.setText("You lose! The dealer scored more points than you!");

            } else {
                failLabel.setText("You went over and played!");
            }

            hitButton.setDisable(true);
            standButton.setDisable(true);
            doubleDownButton.setDisable(true);

            nextGameButton.setDisable(false);
            nextGameButton.setStyle("-fx-background-color: green; -fx-background-radius: 10;");

            return;
        }

        int userMoney = Integer.parseInt(moneyLabel.getText());
        int userBid = Integer.parseInt(betLabel.getText());

        double checkInfinityGame = 0;

        if (userScore == 21) {
            informationLabel.setText("You have won! Your bet has tripled");
            checkInfinityGame = userMoney + (userBid * 3);
            userMoney += userBid * 3;

        } else if (userScore > dealerScore && userScore < 21) {
            informationLabel.setText("You have won! You have more points than the dealer!");
            checkInfinityGame = userMoney + (userBid * 2);
            userMoney += userBid * 2;

        } else if (dealerScore > 21) {
            informationLabel.setText("You have won! Dealer got over it!");
            checkInfinityGame = userMoney + (userBid * 2);
            userMoney += userBid * 2;

        } else if (userScore == dealerScore) {
            informationLabel.setText("Draw! Your bet has been returned!");
            checkInfinityGame = userMoney + userBid;
            userMoney += userBid;

        } else if (userScore < dealerScore) {
            failLabel.setText("You lose! The dealer scored more points than you! Your bet has burned down");

        } else {
            failLabel.setText("You went over and played! Your bet is burned");
        }

        if (checkInfinityGame > 2147483547) {

            informationLabel.setText("Congratulations! You have unlocked the endless mode!");

            setMoneyOnAccountFile(-1);
            setMoneyOnLabel();

            hitButton.setDisable(true);
            standButton.setDisable(true);
            doubleDownButton.setDisable(true);

            nextGameButton.setDisable(false);
            nextGameButton.setStyle("-fx-background-color: green; -fx-background-radius: 10;");

            return;

        }

        setMoneyOnAccountFile(userMoney);
        setMoneyOnLabel();

        hitButton.setDisable(true);
        standButton.setDisable(true);
        doubleDownButton.setDisable(true);

        nextGameButton.setDisable(false);
        nextGameButton.setStyle("-fx-background-color: green; -fx-background-radius: 10;");
    }

    private void makeDealerCard() {
        String newPath = "/project/Assets/Cards/";
        Class<?> clazz = getClass();

        String newCard;
        int k = 0;
        int suitsNum = (int) (Math.random() * 4);
        newPath += suitsList[suitsNum] + "/";
        int cardsNum = (int) (Math.random() * 13);
        newPath += cardsList[cardsNum] + ".png";
        int newScore = Integer.parseInt(dealerScoreLabel.getText()) + cardsScore[cardsNum];
        newCard = suitsList[suitsNum] + cardsList[cardsNum];

        if (!allCardsInGame.isEmpty()) {
            for (String s : allCardsInGame) {
                if (s.equals(newCard)) {
                    k++;
                    if (k >=4) {
                        return;
                    }
                }
            }
        }
        allCardsInGame.add(newCard);



        if (cardsNum == 12) {
            dealerAce++;
        }
        if (newScore > 21 && dealerAce > 0) {
            newScore -= 10;
            dealerAce--;
        }

        dealerScoreLabel.setText(String.valueOf(newScore));

        String pathToDealerCard = Objects.requireNonNull(clazz.getResource(newPath)).toExternalForm();
        Image image = new Image(pathToDealerCard);

        if (firstDealerCard.getImage() == null) {
            firstDealerCard.setImage(image);

        } else if (secondDealerCard.getImage() == null) {
            secondDealerCard.setImage(image);

        } else if (thridDealerCard.getImage() == null) {
            thridDealerCard.setImage(image);

        } else if (fourthDealerCard.getImage() == null) {
            fourthDealerCard.setImage(image);

        } else if (fifthDealerCard.getImage() == null) {
            fifthDealerCard.setImage(image);

        } else if (sixthDealerCard.getImage() == null) {
            sixthDealerCard.setImage(image);

        } else if (seventhDealerCard.getImage() == null) {
            seventhDealerCard.setImage(image);

        } else if (eightDealerCard.getImage() == null) {
            eightDealerCard.setImage(image);

        } else if (ninthDealerCard.getImage() == null) {
            ninthDealerCard.setImage(image);

        } else if (tenthDealerCard.getImage() == null) {
            tenthDealerCard.setImage(image);

        } else if (eleventhDealerCard.getImage() == null) {
            eleventhDealerCard.setImage(image);
        }
    }

    private boolean makePlayerCard() {
        informationLabel.setText("");
        failLabel.setText("");

        String newPath = "/project/Assets/Cards/";
        Class<?> clazz = getClass();

        String newCard;
        int k = 0;
        int suitsNum = (int) (Math.random() * 4);
        newPath += suitsList[suitsNum] + "/";
        int cardsNum = (int) (Math.random() * 13);
        newPath += cardsList[cardsNum] + ".png";
        int newScore = Integer.parseInt(scoreLabel.getText()) + cardsScore[cardsNum];
        newCard = suitsList[suitsNum] + cardsList[cardsNum];

        if (!allCardsInGame.isEmpty()) {
            for (String s : allCardsInGame) {
                if (s.equals(newCard)) {
                    k++;
                    if (k >= 4) {
                        return false;
                    }
                }
            }
        }
        allCardsInGame.add(newCard);

        if (cardsNum == 12) {
            userAce++;
        }

        if (userAce > 0 && newScore > 21) {
            informationLabel.setText("Ace points reduced from 11 to 1");
            newScore -= 10;
            userAce--;
        }

        scoreLabel.setText(String.valueOf(newScore));

        String pathToDealerCard = Objects.requireNonNull(clazz.getResource(newPath)).toExternalForm();
        Image image = new Image(pathToDealerCard);

        if (firstPlayerCard.getImage() == null) {
            firstPlayerCard.setImage(image);

        } else if (secondPlayerCard.getImage() == null) {
            secondPlayerCard.setImage(image);

        } else if (thridPlayerCard.getImage() == null) {
            thridPlayerCard.setImage(image);

        } else if (fourthPlayerCard.getImage() == null) {
            fourthPlayerCard.setImage(image);

        } else if (fifthPlayerCard.getImage() == null) {
            fifthPlayerCard.setImage(image);

        } else if (sixthPlayerCard.getImage() == null) {
            sixthPlayerCard.setImage(image);

        } else if (seventhPlayerCard.getImage() == null) {
            seventhPlayerCard.setImage(image);

        } else if (eightPlayerCard.getImage() == null) {
            eightPlayerCard.setImage(image);

        } else if (ninthPlayerCard.getImage() == null) {
            ninthPlayerCard.setImage(image);

        } else if (tenthPlayerCard.getImage() == null) {
            tenthPlayerCard.setImage(image);

        } else if (eleventhPlayerCard.getImage() == null) {
            eleventhPlayerCard.setImage(image);
        }

        if (newScore == 21 || newScore > 21) {
            finishGame();
        }
        return true;
    }

    private void setMoneyOnLabel() {
        String filePath = "src/main/java/project/Authentication/Accounts.txt";
        String loginToCheck = Start.getLogin();

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] fields = line.split(";");
                if (fields.length >= 2 && fields[0].equals(loginToCheck)) {
                    String money = fields[2];
                    moneyLabel.setText(money);
                    break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void setMoneyOnAccountFile(int newMoney) { //Метод получает количество денег, которое необходимо записать в файл с аккаунтами
        String filePath = "src/main/java/project/Authentication/Accounts.txt"; //Запись пути до нужного файла с аккаунтами, относительно данного проекта
        String loginToUpdate = Start.getLogin(); //Запись в переменную логина, под которым вошёл пользователь

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath)); //Создание объектов BufferedReader и BufferedWriter для чтения и записи данных в файл.
             BufferedWriter writer = new BufferedWriter(new FileWriter(filePath + ".tmp"))) {

            String line; //Вводится строковая переменная line для хранения текущей строки файла.

            while ((line = reader.readLine()) != null) { //Запускается цикл while, который бует работать до тех пор, пока строки файла не закончатся
                String[] parts = line.split(";"); //Создаётся массив parts, в который записывается строка, разделяясь на части с помощью разделителя ";"

                if (parts[0].equals(loginToUpdate)) { //Проверяется совпадает ли логин, полученный из строки, с логином, под которым вошёл пользователь
                    parts[2] = String.valueOf(newMoney); //Если совпадает, то для этого логина записывается новое значение денег пользователя
                    if (newMoney < 0) { //Дополнительное условие проверки, которое необходимо для открытия "Бесконечного режима игры"
                        parts[2] = "∞";
                    }

                    StringBuilder updatedLine = new StringBuilder(); //Создаётся объект StringBuilder для построения обновлённой строки
                    for (String part : parts) { //Запускается цикл for, в котором происходит объединение элементов массива parts в строку с помощью разделителя ";"
                        updatedLine.append(part).append(";");
                    }

                    line = updatedLine.toString(); //Обновлённая строка записывается в переменную line
                }

                writer.write(line); //Строка записывается во временный файл
                writer.newLine(); //Во временном файле создаётся новая пустая строка и начинается следующий цикл while
            }
        } catch (IOException e) { //Обработка ошибки для BufferedReader и BufferedWriter
            e.printStackTrace();
        }

        File originalFile = new File(filePath); //Создаётся объект File, в который помещается старый файл
        File tempFile = new File(filePath + ".tmp"); //Создаётся новый файл


        originalFile.delete(); //Удаляется старый файл
        tempFile.renameTo(originalFile); //Обновлённому файлу присваивается имя старого файла
    }
}
