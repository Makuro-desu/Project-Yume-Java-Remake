import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;


public class Game {

    JFrame window;
    JLayeredPane layeredPane;
    JPanel backgroundPanel, titleNamePanel, startButtonPanel, mainTextPanel, optionButtonPanel, affinityScorePanel, datePanel, nameplatePanel;
    JLabel titleText, affinityScoreLabel, affinityScore, dateLabel, nameplateLabel;
    Font titleFont = new Font("Segoe Script", Font.BOLD, 96);
    Font buttonFont = new Font("Times New Roman", Font.PLAIN, 42);
    Font defualtGameFont = new Font("Segoe UI", Font.PLAIN, 24);
    Font buttonFontSmall = new Font("Segoe UI", Font.PLAIN, 20);
    JButton startButton, option1Button, option2Button, option3Button;
    JTextArea mainTextArea;
    Image bgImage;
    Clip backgroundClip;
    int affinityValue;
    String location, date;

    TitleScreenHandler tsHandler = new TitleScreenHandler();
    OptionButtonHandler optionButtonHandler = new OptionButtonHandler();

    public static void main(String[] args) {

        //LAUNCHER//

        new Game();

    }


    //MAIN GAME WINDOW//

    public Game() {


        //Title Screen Background Image//

        bgImage = new ImageIcon("src/images/TitleScreenImage.png").getImage();

        backgroundPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(bgImage, 0, 0, getWidth(), getHeight(), this);
            }
        };
        backgroundPanel.setBounds(0, 0, 1280, 720);
        backgroundPanel.setLayout(null);


        //Window Config//

        window = new JFrame("Project Yume");
        Dimension maxSize = new Dimension(1280, 720);
        window.setSize(1280, 720);
        window.getContentPane().setBackground(new Color(236, 112, 248, 203));
        Image icon = Toolkit.getDefaultToolkit().getImage("src/images/Yume.png");
        window.setIconImage(icon);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setLayout(null);
        window.setMaximumSize(maxSize);
        window.setPreferredSize(maxSize);
        window.setMinimumSize(maxSize);
        window.setResizable(false);
        window.pack();
        window.setLocationRelativeTo(null);




        //Layering//

        layeredPane = new JLayeredPane();
        layeredPane.setBounds(0, 0, 1280, 720);
        window.setContentPane(layeredPane);
        layeredPane.add(backgroundPanel, Integer.valueOf(0));


        //Title Screen//

        titleNamePanel = new JPanel();
        titleNamePanel.setBounds(180, 150, 900, 200);
        titleNamePanel.setOpaque(false);

        titleText = new JLabel("Project Yume");
        titleText.setForeground(Color.BLACK);
        titleText.setFont(titleFont);
        titleNamePanel.add(titleText);


        //Title Screen Start Button//

        startButtonPanel = new JPanel();
        startButtonPanel.setBounds(525, 450, 200, 100);
        startButtonPanel.setOpaque(false);

        startButton = new JButton("Start");
        startButton.setForeground(Color.BLACK);
        startButton.setFont(buttonFont);
        startButton.setContentAreaFilled(false);
        startButton.setBorderPainted(false);
        startButton.addActionListener(tsHandler);

        startButtonPanel.add(startButton);


        //Layer and Component Management//

        layeredPane.add(titleNamePanel, Integer.valueOf(1));
        layeredPane.add(startButtonPanel, Integer.valueOf(1));

        window.setVisible(true);


        //Play BGM//

        playBackgroundMusic("src/music/Shiny Smily Story （Daybreak ver.）.wav");


    }


    //GAME SCREEN SETUP//

    public void createGameScreen() {

        location = "New Game";


        //GAME SCREEN SETUP//


        //Main Text Panel//

        mainTextPanel = new JPanel();
        mainTextPanel.setBounds(0, 515, 1280, 200);
        mainTextPanel.setBackground(new Color(236, 215, 250, 203));
        mainTextPanel.setOpaque(true);


        mainTextArea = new JTextArea();
        mainTextArea.setBounds(30, 100, 1200, 175);
        mainTextArea.setForeground(Color.BLACK);
        mainTextArea.setBackground(new Color(0, 0, 0, 0));
        mainTextArea.setOpaque(false);
        mainTextArea.setFont(defualtGameFont);
        mainTextArea.setLineWrap(true);
        mainTextArea.setEditable(false);


        //Affinity Score Panel//

        affinityScorePanel = new JPanel();
        affinityScorePanel.setBounds(25, 15, 300, 35);
        affinityScorePanel.setBackground(new Color(255, 255, 255, 100));
        affinityScorePanel.setLayout(new GridLayout(1, 2, 75, 0));
        affinityScorePanel.setOpaque(true);

        affinityScoreLabel = new JLabel("  " + "Affinity: ");
        affinityScoreLabel.setFont(defualtGameFont);
        affinityScoreLabel.setForeground(Color.BLACK);

        affinityScore = new JLabel();
        affinityScore.setFont(defualtGameFont);
        affinityScore.setForeground(Color.BLACK);


        //Date Panel//

        datePanel = new JPanel();
        datePanel.setBounds(955, 15, 300, 35);
        datePanel.setBackground(new Color(236, 215, 250, 100));
        datePanel.setLayout(new GridLayout(1, 1, 0, 0));
        datePanel.setOpaque(true);

        dateLabel = new JLabel();
        dateLabel.setFont(defualtGameFont);
        dateLabel.setForeground(Color.BLACK);


        //Nameplate Panel//

        nameplatePanel = new JPanel();
        nameplatePanel.setBounds(25, 470, 300, 35);
        nameplatePanel.setBackground(new Color(240, 196, 250, 225));
        nameplatePanel.setLayout(new GridLayout(1, 1, 0, 0));
        nameplatePanel.setOpaque(true);

        nameplateLabel = new JLabel();
        nameplateLabel.setFont(defualtGameFont);
        nameplateLabel.setForeground(Color.BLACK);



        //Button Panels//


        optionButtonPanel = new JPanel(new BorderLayout());
        optionButtonPanel.setLayout(new GridLayout(3, 1));
        optionButtonPanel.setBounds(850, 385, 400, 120);
        optionButtonPanel.setOpaque(true);

        option1Button = new JButton();
        option1Button.setBackground(new Color(189, 150, 158));
        option1Button.setForeground(Color.BLACK);
        option1Button.setFont(buttonFontSmall);
        option1Button.setFocusPainted(false);
        option1Button.setActionCommand("optionOne");
        option1Button.addActionListener(optionButtonHandler);

        option2Button = new JButton();
        option2Button.setBackground(new Color(189, 150, 158));
        option2Button.setForeground(Color.BLACK);
        option2Button.setFont(buttonFontSmall);
        option2Button.setFocusPainted(false);
        option2Button.setActionCommand("optionTwo");
        option2Button.addActionListener(optionButtonHandler);

        option3Button = new JButton();
        option3Button.setBackground(new Color(189, 150, 158));
        option3Button.setForeground(Color.BLACK);
        option3Button.setFont(buttonFontSmall);
        option3Button.setFocusPainted(false);
        option3Button.setActionCommand("optionThree");
        option3Button.addActionListener(optionButtonHandler);


        //Layer and Component Management//

        mainTextPanel.add(mainTextArea);
        layeredPane.add(nameplatePanel, Integer.valueOf(1));
        layeredPane.add(datePanel, Integer.valueOf(1));
        layeredPane.add(mainTextPanel, Integer.valueOf(1));
        layeredPane.add(affinityScorePanel, Integer.valueOf(1));
        affinityScorePanel.add(affinityScoreLabel);
        affinityScorePanel.add(affinityScore);
        datePanel.add(dateLabel);
        nameplatePanel.add(nameplateLabel);
        layeredPane.add(optionButtonPanel, Integer.valueOf(1));
        optionButtonPanel.add(option1Button);
        optionButtonPanel.add(option2Button);
        optionButtonPanel.add(option3Button);

        gameStart();

    }

    //GAME START//

    public void gameStart() {
        affinityValue = 0;
        affinityScore.setText("" + affinityValue);


        chapterOne();
    }

    public void chapterOne() {

        location = "Bedroom day 1 (night)";

        //Change BGM//

        playBackgroundMusic("src/music/If I Can Stop One Heart From Breaking (Instrumental).wav");


        //Changing to Bedroom (Night) Scene//

        dateLabel.setText("     " + "June 15th, 2019");

        nameplateLabel.setText("  " + "You (Internal Monologue)");


        bgImage = new ImageIcon("src/images/BedroomNight.png").getImage();
        backgroundPanel.repaint();

        titleNamePanel.setVisible(false);
        startButtonPanel.setVisible(false);

        mainTextArea.setText("Everynight… for months now… I experience the same dream. Or rather, I get the same" +
                " dream right after a nightmare. Though I have no recollection of the nightmares when I wake up, I " +
                "remember the dream right after so vividly. A beautiful girl, about my age or maybe older. Long black" +
                " hair, eyes as vibrant as stars in the night sky, and a smile that radiates a gentle warmth. That's " +
                "right… I remember… that smile. Every time I meet her in the dream, she has a mesmerizing smile on her face.");


        option1Button.setText("Next");
        option2Button.setText("");
        option3Button.setText("");


        //Layer and Component Management//


    }

    public void chapterOnePartTwo() {

        location = "Yume Realm Day 1, Dialogue 1";

        //Changing to Yume Realm 1 Scene//

        bgImage = new ImageIcon("src/images/YumeRealm1.png").getImage();
        backgroundPanel.repaint();

        nameplateLabel.setText("  " + "Yume");
        nameplatePanel.repaint();


        mainTextArea.setText("Glad to see you’re doing well...");

        option1Button.setText("Who are you?");
        option2Button.setText("Hi. Uhm... Do I know you?");
        option3Button.setText("Say Nothing");


    }

    public void chapterOnePartTwoOptionOne() {

        location = "Yume Realm Day 1, Dialogue 2";

        bgImage = new ImageIcon("src/images/YumeRealm1.png").getImage();
        backgroundPanel.repaint();
        mainTextPanel.repaint();

        nameplateLabel.setText("  " + "Yume");
        nameplatePanel.repaint();

        mainTextArea.setText("“Ah, I see. Well then, let me introduce myself. The name is Yume. You must be wondering " +
                "what all of this is \n" + "about?”");
        mainTextArea.revalidate();
        mainTextArea.repaint();

        option1Button.setText("Nice to meet you, Yume.");
        option2Button.setText("You.. know me?");
        option3Button.setText("What do you want from me!");

    }

    public void chapterOnePartTwoOptionTwo() {


        location = "Yume Realm Day 1, Dialogue 3";

        bgImage = new ImageIcon("src/images/YumeRealm1.png").getImage();
        backgroundPanel.repaint();
        mainTextPanel.repaint();

        nameplateLabel.setText("  " + "Yume");
        nameplatePanel.repaint();

        mainTextArea.setText("Are you alright? What's with the look on your face?");
        mainTextArea.revalidate();
        mainTextArea.repaint();

        option1Button.setText("Next");
        option2Button.setText("");
        option3Button.setText("");


    }

    public void chapterOnePartThreeOptionOne() {

        location = "Yume Realm Day 1, Dialogue 4";

        affinityValue = affinityValue + 5;
        affinityScore.setText("" + affinityValue);

        bgImage = new ImageIcon("src/images/YumeRealm1.png").getImage();
        backgroundPanel.repaint();
        mainTextPanel.repaint();
        affinityScorePanel.repaint();

        nameplateLabel.setText("  " + "Yume");
        nameplatePanel.repaint();

        mainTextArea.setText("“Hmm. I wish I could tell you what's going on. But it looks like you aren’t ready yet." +
                " But don’t worry, it may \n" + "take some time but you will definitely get the answer you are looking for.”");
        mainTextArea.revalidate();
        mainTextArea.repaint();

        option1Button.setText("Next");
        option2Button.setText("");
        option3Button.setText("");

    }

    public void chapterOnePartThreeOptionTwo() {

        location = "Yume Realm Day 1, Dialogue 5";

        affinityValue = affinityValue + 4;
        affinityScore.setText("" + affinityValue);
        affinityScorePanel.repaint();

        bgImage = new ImageIcon("src/images/YumeRealm1.png").getImage();
        backgroundPanel.repaint();
        mainTextPanel.repaint();

        nameplateLabel.setText("  " + "Yume");
        nameplatePanel.repaint();

        mainTextArea.setText("“Hmm. I wish I could tell you what's going on. But it looks like you aren’t ready yet." +
                " But don’t worry, it may \n" + " take some time but you will definitely get the answer you are looking for.”");
        mainTextArea.revalidate();
        mainTextArea.repaint();

        option1Button.setText("Next");
        option2Button.setText("");
        option3Button.setText("");

    }


    public void chapterOnePartThreeOptionThree() {

        location = "Yume Realm Day 1, Dialogue 6";

        affinityValue = affinityValue + 3;
        affinityScore.setText("" + affinityValue);
        affinityScorePanel.repaint();

        bgImage = new ImageIcon("src/images/YumeRealm1.png").getImage();
        backgroundPanel.repaint();
        mainTextPanel.repaint();

        nameplateLabel.setText("  " + "Yume");
        nameplatePanel.repaint();

        mainTextArea.setText("“Hmm. I wish I could tell you what's going on. But it looks like you aren’t ready yet." +
                " But don’t worry, it may \n" + " take some time but you will definitely get the answer you are looking for.”");
        mainTextArea.revalidate();
        mainTextArea.repaint();

        option1Button.setText("Next");
        option2Button.setText("");
        option3Button.setText("");

    }

    public void chapterOnePartFour() {

        location = "Yume Realm Day 1, Dialogue 7";

        bgImage = new ImageIcon("src/images/YumeRealm1.png").getImage();
        backgroundPanel.repaint();
        mainTextPanel.repaint();

        nameplateLabel.setText("  " + "Yume");
        nameplatePanel.repaint();

        mainTextArea.setText("“...And besides, It's almost time for you to wake up. Don’t worry, you will see me again. " +
                "I’ll be here as always \n" + "waiting for you.”");
        mainTextArea.revalidate();
        mainTextArea.repaint();

        option1Button.setText("Next");
        option2Button.setText("");
        option3Button.setText("");

    }

    public void chapterOnePartFive() {

        //Change BGM//

        playBackgroundMusic("src/music/[Zenless Zone Zero OST] 60% Daily - Freedom.wav");

        location = "Bedroom day 1 (morning), Dialogue 1";

        dateLabel.setText("     " + "June 16th, 2019");
        datePanel.repaint();

        bgImage = new ImageIcon("src/images/BedroomDay.png").getImage();
        backgroundPanel.repaint();
        mainTextPanel.repaint();

        nameplateLabel.setText("  " + "You (Internal Monologue)");
        nameplatePanel.repaint();

        mainTextArea.setText("As the dream fades out and the sound of my morning alarm fades in, I come to my senses. " +
                "I wonder where this is gonna take me.");
        mainTextArea.revalidate();
        mainTextArea.repaint();

        option1Button.setText("Next");
        option2Button.setText("");
        option3Button.setText("");



        //CHAPTER TWO//


    }

    public void chapterTwoIntro() {

        playBackgroundMusic("src/music/If I Can Stop One Heart From Breaking (Instrumental).wav");

        location = "Bedroom day 2 (night), Dialogue 1";

        dateLabel.setText("     " + "June 22nd, 2019");
        datePanel.repaint();

        bgImage = new ImageIcon("src/images/BedroomNight.png").getImage();
        backgroundPanel.repaint();
        mainTextPanel.repaint();

        nameplateLabel.setText("  ");
        nameplatePanel.repaint();

        mainTextArea.setText("C  H  A  P  T  E  R    T  W  O");
        mainTextArea.revalidate();
        mainTextArea.repaint();

        option1Button.setText("Next");
        option2Button.setText("");
        option3Button.setText("");

    }


    public void chapterTwoPartOne() {

        location = "Bedroom day 2 (night), Dialogue 2";


        bgImage = new ImageIcon("src/images/BedroomNight.png").getImage();
        backgroundPanel.repaint();
        mainTextPanel.repaint();

        nameplateLabel.setText("  " + "You (Internal Monologue");
        nameplatePanel.repaint();

        mainTextArea.setText("This time, we got to talk a little bit more. Yume asked how my day went and intently " +
                "listened as I talked about my mundane daily routine and what went on during the day at work. The " +
                "thought of all this being weird is still in the back of my mind. But oddly enough, I am at peace. I " +
                "feel like a little bit of weight was taken off my shoulders. On top of that, she is a sight for sore " +
                "eyes. Now that I think about it, I kinda wish she was real.");

        mainTextArea.revalidate();
        mainTextArea.repaint();

        option1Button.setText("Next");
        option2Button.setText("");
        option3Button.setText("");


    }


    public void chapterTwoPartTwo() {

        location = "Yume Realm Day 2, Dialogue 1";


        bgImage = new ImageIcon("src/images/YumeRealm1.png").getImage();
        backgroundPanel.repaint();
        mainTextPanel.repaint();

        nameplateLabel.setText("  " + "Yume");
        nameplatePanel.repaint();

        mainTextArea.setText("“Sounds like you had an eventful day.”");

        mainTextArea.revalidate();
        mainTextArea.repaint();

        option1Button.setText("Yeah! It was very productive.");
        option2Button.setText("Really? To me it’s just another day really.");
        option3Button.setText("");


    }


    public void chapterTwoPartThree() {

        location = "Yume Realm Day 2, Dialogue 2";


        bgImage = new ImageIcon("src/images/YumeRealm1.png").getImage();
        backgroundPanel.repaint();
        mainTextPanel.repaint();

        nameplateLabel.setText("  " + "You");
        nameplatePanel.repaint();

        mainTextArea.setText("But, seeing you here is the best part of my day.");

        mainTextArea.revalidate();
        mainTextArea.repaint();

        option1Button.setText("Next");
        option2Button.setText("");
        option3Button.setText("");

    }

    public void chapterTwoPartFour() {

        location = "Yume Realm Day 2, Dialogue 3";

        bgImage = new ImageIcon("src/images/YumeRealm1.png").getImage();
        backgroundPanel.repaint();
        mainTextPanel.repaint();

        nameplateLabel.setText("  " + "You (Internal Monologue)");
        nameplatePanel.repaint();

        mainTextArea.setText("Her vibrant smile turned into a more faint one with a hint of concern when I said that. " +
                "Did I happen to say something to offend her? I hesitantly broke the awkward silence by asking random " +
                "questions about her. Which she responded to vaguely. Well, some of them at least.");

        mainTextArea.revalidate();
        mainTextArea.repaint();

        option1Button.setText("Can you tell me something about yourself?");
        option2Button.setText("Why do I keep seeing you in my dreams?");
        option3Button.setText("The nightmares, do you know anything about them?");


    }


    public void chapterTwoPartFiveRouteOne() {

        location = "Yume Realm Day 2, Dialogue 4";

        affinityValue = affinityValue + 5;
        affinityScore.setText("" + affinityValue);

        bgImage = new ImageIcon("src/images/YumeRealm1.png").getImage();
        backgroundPanel.repaint();
        mainTextPanel.repaint();
        affinityScorePanel.repaint();

        nameplateLabel.setText("  " + "Yume");
        nameplatePanel.repaint();

        mainTextArea.setText("“Hmmm… I don’t know if right now is the right time to answer these questions now. But I " +
                "can tell you this, I am here because of you. It may be hard to take my word right now but know that my" +
                " intentions are not to cause you any harm. ”");

        mainTextArea.revalidate();
        mainTextArea.repaint();

        option1Button.setText("Next");
        option2Button.setText("");
        option3Button.setText("");


    }


    public void chapterTwoPartFiveRouteTwo() {

        location = "Yume Realm Day 2, Dialogue 5";

        affinityValue = affinityValue + 4;
        affinityScore.setText("" + affinityValue);

        bgImage = new ImageIcon("src/images/YumeRealm1.png").getImage();
        backgroundPanel.repaint();
        mainTextPanel.repaint();
        affinityScorePanel.repaint();

        nameplateLabel.setText("  " + "Yume");
        nameplatePanel.repaint();

        mainTextArea.setText("“Hmmm… I don’t know if right now is the right time to answer these questions now. But I " +
                "can tell you this, I am here because of you. It may be hard to take my word right now but know that my" +
                " intentions are not to cause you any harm. ”");

        mainTextArea.revalidate();
        mainTextArea.repaint();

        option1Button.setText("Next");
        option2Button.setText("");
        option3Button.setText("");


    }

    public void chapterTwoPartFiveRouteThree() {

        location = "Yume Realm Day 2, Dialogue 6";

        affinityValue = affinityValue + 3;
        affinityScore.setText("" + affinityValue);

        bgImage = new ImageIcon("src/images/YumeRealm1.png").getImage();
        backgroundPanel.repaint();
        mainTextPanel.repaint();
        affinityScorePanel.repaint();

        nameplateLabel.setText("  " + "Yume");
        nameplatePanel.repaint();

        mainTextArea.setText("“Hmmm… I don’t know if right now is the right time to answer these questions now. But I " +
                "can tell you this, I am here because of you. It may be hard to take my word right now but know that my" +
                " intentions are not to cause you any harm. ”");

        mainTextArea.revalidate();
        mainTextArea.repaint();

        option1Button.setText("Next");
        option2Button.setText("");
        option3Button.setText("");


    }


    public void chapterTwoPartSix() {

        location = "Yume Realm Day 2, Dialogue 7";


        bgImage = new ImageIcon("src/images/YumeRealm1.png").getImage();
        backgroundPanel.repaint();
        mainTextPanel.repaint();
        affinityScorePanel.repaint();

        nameplateLabel.setText("  " + "Yume");
        nameplatePanel.repaint();

        mainTextArea.setText("“Intentions might not even be the right way to put it. Ah, that's it. Purpose. I have a " +
                "purpose that I need to fulfill. All you need to know for now is that whatever this purpose is, it is " +
                "for you.”");

        mainTextArea.revalidate();
        mainTextArea.repaint();

        option1Button.setText("It is frustrating, but for now I will trust you, Yume.");
        option2Button.setText("Can I really trust you?");
        option3Button.setText("");


    }


    public void chapterTwoPartSevenRouteOne() {

        affinityValue = affinityValue + 5;
        affinityScore.setText("" + affinityValue);

        location = "Yume Realm Day 2, Dialogue 8";


        bgImage = new ImageIcon("src/images/YumeRealm1.png").getImage();
        backgroundPanel.repaint();
        mainTextPanel.repaint();
        affinityScorePanel.repaint();

        nameplateLabel.setText("  " + "Yume");
        nameplatePanel.repaint();

        mainTextArea.setText("“Thank you. Like I said, when it is time I will tell you everything about me and my purpose. " +
                "It’s almost time for you to wake up soon. As always, I will be here waiting.”");


        mainTextArea.revalidate();
        mainTextArea.repaint();

        option1Button.setText("Next");
        option2Button.setText("");
        option3Button.setText("");


    }



    public void chapterTwoPartSevenRouteTwo() {

        affinityValue = affinityValue + 4;
        affinityScore.setText("" + affinityValue);

        location = "Yume Realm Day 2, Dialogue 9";

        nameplateLabel.setText("  " + "Yume");
        nameplatePanel.repaint();


        bgImage = new ImageIcon("src/images/YumeRealm1.png").getImage();
        backgroundPanel.repaint();
        mainTextPanel.repaint();
        affinityScorePanel.repaint();

        mainTextArea.setText("“I cannot force you to trust me and there is nothing I can say right now that will convince" +
                " you. All I ask is that you give it just a little bit of time. Please… It's time for you to wake up." +
                " I'll be waiting here as always.”");

        mainTextArea.revalidate();
        mainTextArea.repaint();

        option1Button.setText("Next");
        option2Button.setText("");
        option3Button.setText("");


    }


    public void chapterTwoPartEight(){

        playBackgroundMusic("src/music/[Zenless Zone Zero OST] 60% Daily - Freedom.wav");

        location = "Bedroom day 2 (morning), Dialogue 1";

        dateLabel.setText("     " + "June 23rd, 2019");
        datePanel.repaint();

        nameplateLabel.setText("  " + "You (Internal Monologue)");
        nameplatePanel.repaint();


        bgImage = new ImageIcon("src/images/BedroomDay.png").getImage();
        backgroundPanel.repaint();
        mainTextPanel.repaint();
        affinityScorePanel.repaint();

        mainTextArea.setText("I once again, wake up to my alarm. Same old routine as always but this time, I have something" +
                " to look forward to at the end of the day…");

        mainTextArea.revalidate();
        mainTextArea.repaint();

        option1Button.setText("Next");
        option2Button.setText("");
        option3Button.setText("");


    }


    //CHAPTER 3//


    public void chapterThreeIntro() {

        playBackgroundMusic("src/music/The Way Home ｜ Zenless Zone Zero OST (Version 1.2).wav");

        location = "Bedroom day 3 (night), Dialogue 1";

        dateLabel.setText("     " + "May 19, 2018");
        datePanel.repaint();

        bgImage = new ImageIcon("src/images/LuminaSquareNight.PNG").getImage();
        backgroundPanel.repaint();
        mainTextPanel.repaint();

        nameplateLabel.setText("  ");
        nameplatePanel.repaint();

        mainTextArea.setText("C  H  A  P  T  E  R     T  H R E E \n\n" + "About a year ago...");
        mainTextArea.revalidate();
        mainTextArea.repaint();

        option1Button.setText("Next");
        option2Button.setText("");
        option3Button.setText("");

    }


    public void chapterThreePartOne() {

        location = "Bedroom day 3 (night), Dialogue 2";


        bgImage = new ImageIcon("src/images/LuminaSquareNight.PNG").getImage();
        backgroundPanel.repaint();
        mainTextPanel.repaint();

        nameplateLabel.setText("  " + "You (Internal Monologue)");
        nameplatePanel.repaint();

        mainTextArea.setText("I feel empty… I am supposed to be sad. Devastated even. Maybe I’m still in shock. I had never " +
                "imagined that this day would come. As the minutes go by, it slowly sinks in… I am alone now. The only " +
                "person that matters to me, the only one I wanted to spend the rest of my life with, no longer wants to be " +
                "a part of the future I envisioned for us. It was the meaning of my life… now it's gone. As the pain settled " +
                "in, the tears followed. I know this is real. But a part of me still hopes this was all a dream.");


        mainTextArea.revalidate();
        mainTextArea.repaint();

        option1Button.setText("Next");
        option2Button.setText("");
        option3Button.setText("");


    }


    public void chapterThreePartTwo() {

        playBackgroundMusic("src/music/If I Can Stop One Heart From Breaking (Instrumental).wav");

        location = "Bedroom day 3 (night), Dialogue 3";

        dateLabel.setText("     " + "July 2nd, 2019");
        datePanel.repaint();

        nameplateLabel.setText("  ");
        nameplatePanel.repaint();



        bgImage = new ImageIcon("src/images/BedroomNight.png").getImage();
        backgroundPanel.repaint();
        mainTextPanel.repaint();

        mainTextArea.setText("Present Day...");


        mainTextArea.revalidate();
        mainTextArea.repaint();

        option1Button.setText("Next");
        option2Button.setText("");
        option3Button.setText("");


    }


    public void chapterThreePartThree() {

        location = "Yume Realm Day 3, Dialogue 1";


        bgImage = new ImageIcon("src/images/YumeRealm2.png").getImage();
        backgroundPanel.repaint();
        mainTextPanel.repaint();

        nameplateLabel.setText("  " + "You (Internal Monologue)");
        nameplatePanel.repaint();

        mainTextArea.setText("It was the same as usual when I fell asleep. But this time for some reason, I remember the" +
                " nightmare. It's a bit foggy, but I think I know what it was about. Because the feeling I got from it was" +
                " a very familiar feeling. The pain and the crippling weight of your entire world falling apart on you. It was just like that day… No matter, " +
                "I will be seeing Yume soon. That's weird, she’s usually already here by this time.");


        mainTextArea.revalidate();
        mainTextArea.repaint();

        option1Button.setText("Next");
        option2Button.setText("");
        option3Button.setText("");


    }


    public void chapterThreePartFour() {

        location = "Yume Realm Day 3, Dialogue 2";


        bgImage = new ImageIcon("src/images/YumeRealm3v2.png").getImage();
        backgroundPanel.repaint();
        mainTextPanel.repaint();

        nameplateLabel.setText("  " + "Yume");
        nameplatePanel.repaint();

        mainTextArea.setText("Hey… Sorry I kept you waiting.");


        mainTextArea.revalidate();
        mainTextArea.repaint();

        option1Button.setText("Yume. Are you alright? You look exhausted.");
        option2Button.setText("Whoa. Where have you been?");
        option3Button.setText("");


    }


    public void chapterThreePartFiveRouteOne() {

        affinityValue = affinityValue + 5;
        affinityScore.setText("" + affinityValue);

        location = "Yume Realm Day 3, Dialogue 3";

        nameplateLabel.setText("  " + "Yume");
        nameplatePanel.repaint();


        bgImage = new ImageIcon("src/images/YumeRealm3v2.png").getImage();
        backgroundPanel.repaint();
        mainTextPanel.repaint();

        mainTextArea.setText("I’m… fine. Just a little winded, that's all. Don’t worry about it.");


        mainTextArea.revalidate();
        mainTextArea.repaint();

        option1Button.setText("Next");
        option2Button.setText("");
        option3Button.setText("");


    }


    public void chapterThreePartFiveRouteTwo() {

        affinityValue = affinityValue + 4;
        affinityScore.setText("" + affinityValue);

        location = "Yume Realm Day 3, Dialogue 4";

        nameplateLabel.setText("  " + "Yume");
        nameplatePanel.repaint();


        bgImage = new ImageIcon("src/images/YumeRealm3v2.png").getImage();
        backgroundPanel.repaint();
        mainTextPanel.repaint();

        mainTextArea.setText("I’m… fine. Just a little winded, that's all. Don’t worry about it.");


        mainTextArea.revalidate();
        mainTextArea.repaint();

        option1Button.setText("Next");
        option2Button.setText("");
        option3Button.setText("");


    }


    public void chapterThreePartSix() {

        location = "Yume Realm Day 3, Dialogue 5";


        bgImage = new ImageIcon("src/images/YumeRealm3v2.png").getImage();
        backgroundPanel.repaint();
        mainTextPanel.repaint();

        nameplateLabel.setText("  " + "You (Internal Monologue)");
        nameplatePanel.repaint();

        mainTextArea.setText("She was obviously lying. But the fact that I can remember the nightmare I just had and my " +
                "suspicion that Yume has something to do with all of this, I think it would be safe to assume that something is going terribly wrong. \n");


        mainTextArea.revalidate();
        mainTextArea.repaint();

        option1Button.setText("Next");
        option2Button.setText("");
        option3Button.setText("");


    }


    public void chapterThreePartSeven() {

        location = "Yume Realm Day 3, Dialogue 6";


        bgImage = new ImageIcon("src/images/YumeRealm3v2.png").getImage();
        backgroundPanel.repaint();
        mainTextPanel.repaint();

        nameplateLabel.setText("  " + "Yume");
        nameplatePanel.repaint();

        mainTextArea.setText("I know I just got here, but it's about time for you to wake up.");


        mainTextArea.revalidate();
        mainTextArea.repaint();

        option1Button.setText("Next");
        option2Button.setText("");
        option3Button.setText("");


    }


    public void chapterThreePartEight() {

        location = "Yume Realm Day 3, Dialogue 7";


        bgImage = new ImageIcon("src/images/YumeRealm3v2.png").getImage();
        backgroundPanel.repaint();
        mainTextPanel.repaint();

        nameplateLabel.setText("  " + "You");
        nameplatePanel.repaint();

        mainTextArea.setText("I don’t really understand. But if you say so then I will go.");


        mainTextArea.revalidate();
        mainTextArea.repaint();

        option1Button.setText("Next");
        option2Button.setText("");
        option3Button.setText("");


    }


    public void chapterThreePartNine() {

        location = "Yume Realm Day 3, Dialogue 8";


        bgImage = new ImageIcon("src/images/YumeRealm3v2.png").getImage();
        backgroundPanel.repaint();
        mainTextPanel.repaint();

        nameplateLabel.setText("  " + "Yume");
        nameplatePanel.repaint();

        mainTextArea.setText("I know you’re smart so you’ll probably have your answer by the next time we meet." +
                " I promise to tell you everything by then. But for now please, you need to leave. It's not safe for you to be here right now.");


        mainTextArea.revalidate();
        mainTextArea.repaint();

        option1Button.setText("Next");
        option2Button.setText("");
        option3Button.setText("");


    }


    public void chapterThreePartTen() {

        playBackgroundMusic("src/music/[Zenless Zone Zero OST] 60% Daily - Freedom.wav");


        dateLabel.setText("     " + "July 3rd, 2019");
        datePanel.repaint();

        location = "Bedroom day 3 (morning), Dialogue 1";


        bgImage = new ImageIcon("src/images/BedroomDay.png").getImage();
        backgroundPanel.repaint();
        mainTextPanel.repaint();

        nameplateLabel.setText("  " + "You (Internal Monologue)");
        nameplatePanel.repaint();

        mainTextArea.setText("As I woke up, I sat up to gather my thoughts. Thinking about what Yume said before I woke" +
                " up, I got reminded of an old folktale. About a mythical creature who devours nightmares. The “Baku”.");


        mainTextArea.revalidate();
        mainTextArea.repaint();

        option1Button.setText("Next");
        option2Button.setText("");
        option3Button.setText("");


    }



    //FINALE//



    public void finaleIntro() {



        dateLabel.setText("     " + "July 3rd, 2019");
        datePanel.repaint();

        location = "Bedroom day 3 (morning), Dialogue 2";


        bgImage = new ImageIcon("src/images/BedroomDay.png").getImage();
        backgroundPanel.repaint();
        mainTextPanel.repaint();

        nameplateLabel.setText("  " + "You (Internal Monologue)");
        nameplatePanel.repaint();

        mainTextArea.setText(" I spent the entire day reading up on a mythical creature called the “Baku”. The legends" +
                " suggest that this creature devours people's nightmares helping them to sleep peacefully. In the legends " +
                "however, it is said that the Baku can also devour someone’s hopes and dreams when they are not satisfied.");


        mainTextArea.revalidate();
        mainTextArea.repaint();

        option1Button.setText("Next");
        option2Button.setText("");
        option3Button.setText("");


    }


    public void finaleIntroPartTwo() {

        playBackgroundMusic("src/music/If I Can Stop One Heart From Breaking (Instrumental).wav");

        location = "Bedroom day 3 (morning), Dialogue 3";


        bgImage = new ImageIcon("src/images/BedroomNight.png").getImage();
        backgroundPanel.repaint();
        mainTextPanel.repaint();

        nameplateLabel.setText("  " + "You (Internal Monologue)");
        nameplatePanel.repaint();

        mainTextArea.setText("Yume does not seem to be like that. She only takes my nightmares and even lets me remember" +
                        " our interactions in the dream. But… why? I thought about it for a while and as the hours went by," +
                        " I realized it's almost time for me to go to sleep.");


        mainTextArea.revalidate();
        mainTextArea.repaint();

        option1Button.setText("Next");
        option2Button.setText("");
        option3Button.setText("");


    }


    public void finalePartOne() {


        location = "Yume Realm day 4, Dialogue 1";


        bgImage = new ImageIcon("src/images/YumeRealm3v2.png").getImage();
        backgroundPanel.repaint();
        mainTextPanel.repaint();

        nameplateLabel.setText("  " + "You");
        nameplatePanel.repaint();

        mainTextArea.setText("Yume… you look just as exhausted as yesterday.");


        mainTextArea.revalidate();
        mainTextArea.repaint();

        option1Button.setText("Next");
        option2Button.setText("");
        option3Button.setText("");


    }


    public void finalePartTwo() {


        location = "Yume Realm day 4, Dialogue 2";


        bgImage = new ImageIcon("src/images/YumeRealm3v2.png").getImage();
        backgroundPanel.repaint();
        mainTextPanel.repaint();

        nameplateLabel.setText("  " + "Yume");
        nameplatePanel.repaint();

        mainTextArea.setText("Don’t worry about me. What’s more important is that you already have the answer, am I right?”");


        mainTextArea.revalidate();
        mainTextArea.repaint();

        option1Button.setText("Yes...");
        option2Button.setText("");
        option3Button.setText("");

    }


    public void finalePartThree() {

        playBackgroundMusic("src/music/If I Can Stop One Heart From Breaking (Encore).wav");


        location = "Yume Realm day 4, Dialogue 3";


        bgImage = new ImageIcon("src/images/YumeRealm3v2.png").getImage();
        backgroundPanel.repaint();
        mainTextPanel.repaint();

        nameplateLabel.setText("  " + "Yume");
        nameplatePanel.repaint();

        mainTextArea.setText("I am a Baku. But you probably know that already. We devour nightmares but some of us also " +
                "devour hopes and dreams. As for me, I only want to help you. I want to ease the pain in your heart.");


        mainTextArea.revalidate();
        mainTextArea.repaint();

        option1Button.setText("Next");
        option2Button.setText("");
        option3Button.setText("");

    }


    public void finalePartFour() {


        location = "Yume Realm day 4, Dialogue 4";


        bgImage = new ImageIcon("src/images/YumeRealm3v2.png").getImage();
        backgroundPanel.repaint();
        mainTextPanel.repaint();

        nameplateLabel.setText("  " + "Yume");
        nameplatePanel.repaint();

        mainTextArea.setText("As you can see, I have become weaker as my powers are almost spent. We “devour” nightmares, " +
                "but it's better to describe it as “subduing” the nightmare and sealing it within ourselves.");


        mainTextArea.revalidate();
        mainTextArea.repaint();

        option1Button.setText("Next");
        option2Button.setText("");
        option3Button.setText("");

    }



    // ENDINGS //





    //Good Ending Route//

    public void goodEndingPartOne() {

        playBackgroundMusic("src/music/If I Can Stop One Heart From Breaking - Honkai： Star Rail 2.0 OST.wav");


        location = "Yume Realm day 4, Dialogue 5";


        bgImage = new ImageIcon("src/images/YumeRealm3v2.png").getImage();
        backgroundPanel.repaint();
        mainTextPanel.repaint();

        nameplateLabel.setText("  " + "You");
        nameplatePanel.repaint();

        mainTextArea.setText("So that means, everytime I have a nightmare…");


        mainTextArea.revalidate();
        mainTextArea.repaint();

        option1Button.setText("Next");
        option2Button.setText("");
        option3Button.setText("");

    }


    public void goodEndingPartTwo() {


        location = "Yume Realm day 4, Dialogue 6";


        bgImage = new ImageIcon("src/images/YumeRealm3v2.png").getImage();
        backgroundPanel.repaint();
        mainTextPanel.repaint();

        nameplateLabel.setText("  " + "Yume");
        nameplatePanel.repaint();

        mainTextArea.setText("Hey now. Don’t make that face. None of this is your fault. This is my purpose. The reason " +
                "I exist. I am not bound to fulfill this purpose. But I choose to do it, because it's you.");


        mainTextArea.revalidate();
        mainTextArea.repaint();

        option1Button.setText("Next");
        option2Button.setText("");
        option3Button.setText("");

    }


    public void goodEndingPartThree() {


        location = "Yume Realm day 4, Dialogue 7";


        bgImage = new ImageIcon("src/images/YumeRealm3v2.png").getImage();
        backgroundPanel.repaint();
        mainTextPanel.repaint();

        nameplateLabel.setText("  " + "You (Internal Monologue)");
        nameplatePanel.repaint();

        mainTextArea.setText("Hearing this, I can’t help but shed a tear. Because it’s me? What does she mean by that?" +
                " But somehow, I am glad.");


        mainTextArea.revalidate();
        mainTextArea.repaint();

        option1Button.setText("Next");
        option2Button.setText("");
        option3Button.setText("");

    }


    public void goodEndingPartFour() {


        location = "Yume Realm day 4, Dialogue 8";


        bgImage = new ImageIcon("src/images/YumeRealm3v2.png").getImage();
        backgroundPanel.repaint();
        mainTextPanel.repaint();

        nameplateLabel.setText("  " + "Yume");
        nameplatePanel.repaint();

        mainTextArea.setText("I don’t know what it is and how it started. But as the time went by I felt a sense of " +
                "warmth in my heart whenever I got to see you. It brought me joy and pushed me to do more for you. I " +
                "feel upset whenever I see your nightmares because I know how much sadness it brought you.");


        mainTextArea.revalidate();
        mainTextArea.repaint();

        option1Button.setText("Next");
        option2Button.setText("");
        option3Button.setText("");

    }


    public void goodEndingPartFive() {


        location = "Yume Realm day 4, Dialogue 9";


        bgImage = new ImageIcon("src/images/YumeRealm3v2.png").getImage();
        backgroundPanel.repaint();
        mainTextPanel.repaint();

        nameplateLabel.setText("  " + "You (Internal Monologue)");
        nameplatePanel.repaint();

        mainTextArea.setText("I wanted to say something, but I couldn't find the words. Without fully realizing it, " +
                "I have already embraced her. I have never felt this valued for a very long time. I want to be with Yume." +
                " But I know we don’t have much time left.");


        mainTextArea.revalidate();
        mainTextArea.repaint();

        option1Button.setText("Next");
        option2Button.setText("");
        option3Button.setText("");

    }


    public void goodEndingPartSix() {


        location = "Yume Realm day 4, Dialogue 10";


        bgImage = new ImageIcon("src/images/YumeRealm3v2.png").getImage();
        backgroundPanel.repaint();
        mainTextPanel.repaint();

        nameplateLabel.setText("  " + "You");
        nameplatePanel.repaint();

        mainTextArea.setText("I want to thank you… for everything. You were there when no one else was. You protected " +
                "me from being consumed by the pain in my heart. I understand that you are unable to exist in reality but" +
                " a part of me wishes you were real. So I can be with you in reality.");


        mainTextArea.revalidate();
        mainTextArea.repaint();

        option1Button.setText("Next");
        option2Button.setText("");
        option3Button.setText("");

    }


    public void goodEndingPartSeven() {


        location = "Yume Realm day 4, Dialogue 11";


        bgImage = new ImageIcon("src/images/YumeRealm3v2.png").getImage();
        backgroundPanel.repaint();
        mainTextPanel.repaint();

        nameplateLabel.setText("  " + "You (Internal Monologue)");
        nameplatePanel.repaint();

        mainTextArea.setText("I expected Yume to show me her usual gentle smile as a response. But I never expected " +
                "tears in her eyes as she looked at me.");


        mainTextArea.revalidate();
        mainTextArea.repaint();

        option1Button.setText("Next");
        option2Button.setText("");
        option3Button.setText("");

    }


    public void goodEndingPartEight() {


        location = "Yume Realm day 4, Dialogue 12";


        bgImage = new ImageIcon("src/images/YumeRealm3v2.png").getImage();
        backgroundPanel.repaint();
        mainTextPanel.repaint();

        nameplateLabel.setText("  " + "Yume");
        nameplatePanel.repaint();

        mainTextArea.setText("I wish… I was real too. So I can ease your pain in a way that truly matters.");


        mainTextArea.revalidate();
        mainTextArea.repaint();

        option1Button.setText("Next");
        option2Button.setText("");
        option3Button.setText("");

    }


    public void goodEndingPartNine() {


        location = "Yume Realm day 4, Dialogue 13";


        bgImage = new ImageIcon("src/images/YumeRealm3v2.png").getImage();
        backgroundPanel.repaint();
        mainTextPanel.repaint();

        nameplateLabel.setText("  " + "Yume");
        nameplatePanel.repaint();

        mainTextArea.setText("Now, please live on happily. Live your life and seek happiness for yourself. That is all I" +
                " wish for. My power has severely weakened, I will no longer be able to meet you like this. I will spend " +
                "whatever remains of it to seal this nightmare away for good. And then, I will cease to exist.");


        mainTextArea.revalidate();
        mainTextArea.repaint();

        option1Button.setText("Next");
        option2Button.setText("");
        option3Button.setText("");

    }


    public void goodEndingPartTen() {


        location = "Yume Realm day 4, Dialogue 14";


        bgImage = new ImageIcon("src/images/YumeRealm3v2.png").getImage();
        backgroundPanel.repaint();
        mainTextPanel.repaint();

        nameplateLabel.setText("  " + "You");
        nameplatePanel.repaint();

        mainTextArea.setText("I know I have already caused you so much trouble, but I was hoping you can grant me one last wish...?");


        mainTextArea.revalidate();
        mainTextArea.repaint();

        option1Button.setText("Next");
        option2Button.setText("");
        option3Button.setText("");

    }


    public void goodEndingPartEleven() {


        location = "Yume Realm day 4, Dialogue 15";


        bgImage = new ImageIcon("src/images/YumeRealm3v2.png").getImage();
        backgroundPanel.repaint();
        mainTextPanel.repaint();

        nameplateLabel.setText("  " + "Yume");
        nameplatePanel.repaint();

        mainTextArea.setText("You know I will do anything for you.");


        mainTextArea.revalidate();
        mainTextArea.repaint();

        option1Button.setText("Next");
        option2Button.setText("");
        option3Button.setText("");

    }


    public void goodEndingPartTwelve() {


        location = "Yume Realm day 4, Dialogue 16";


        bgImage = new ImageIcon("src/images/YumeRealm3v2.png").getImage();
        backgroundPanel.repaint();
        mainTextPanel.repaint();

        nameplateLabel.setText("  " + "You");
        nameplatePanel.repaint();

        mainTextArea.setText("Please let me keep this memory of you. After you cease to exist, I want to remember you. " +
                "I want to remember the one who saved my life. I want to remember the reason why I am able to find my " +
                "happiness. Please… Yume.");


        mainTextArea.revalidate();
        mainTextArea.repaint();

        option1Button.setText("Next");
        option2Button.setText("");
        option3Button.setText("");

    }


    public void goodEndingPartThirteen() {


        location = "Yume Realm day 4, Dialogue 17";


        bgImage = new ImageIcon("src/images/YumeRealm3v2.png").getImage();
        backgroundPanel.repaint();
        mainTextPanel.repaint();

        nameplateLabel.setText("  " + "Yume");
        nameplatePanel.repaint();

        mainTextArea.setText("If that’s what you wish for, then yes I will grant it to you. The thought of being " +
                "remembered makes me happy.");


        mainTextArea.revalidate();
        mainTextArea.repaint();

        option1Button.setText("Next");
        option2Button.setText("");
        option3Button.setText("");

    }


    public void goodEndingPartFourteen() {


        location = "Yume Realm day 4, Dialogue 18";


        bgImage = new ImageIcon("src/images/YumeRealm3v2.png").getImage();
        backgroundPanel.repaint();
        mainTextPanel.repaint();

        nameplateLabel.setText("  " + "Yume");
        nameplatePanel.repaint();

        mainTextArea.setText("It’s almost time for me to go... I’m so glad to have spent all this time with you.");


        mainTextArea.revalidate();
        mainTextArea.repaint();

        option1Button.setText("Next");
        option2Button.setText("");
        option3Button.setText("");

    }


    public void goodEndingPartFifteen() {


        location = "Yume Realm day 4, Dialogue 19";


        bgImage = new ImageIcon("src/images/YumeRealm3v2.png").getImage();
        backgroundPanel.repaint();
        mainTextPanel.repaint();

        nameplateLabel.setText("  " + "You");
        nameplatePanel.repaint();

        mainTextArea.setText("I’m also very glad to have met you, my dear Yume.");


        mainTextArea.revalidate();
        mainTextArea.repaint();

        option1Button.setText("Next");
        option2Button.setText("");
        option3Button.setText("");

    }


    public void goodEndingPartSixteen() {

        playBackgroundMusic("src/music/If I Can Stop One Heart From Breaking (Encore).wav");


        location = "Bedroom day 4 (morning), Dialogue 1";


        bgImage = new ImageIcon("src/images/BedroomDay.png").getImage();
        backgroundPanel.repaint();
        mainTextPanel.repaint();

        nameplateLabel.setText("  " + "You (Internal Monologue)");
        nameplatePanel.repaint();

        mainTextArea.setText("I had tears in my eyes when I woke up. That is the last time I will ever see her. I want" +
                " to burn her face into my memory. I don’t ever want to forget that smile. From now on, I will find happiness." +
                " I will live my life and pursue my dreams. Because that is what Yume would have wanted.");


        mainTextArea.revalidate();
        mainTextArea.repaint();

        option1Button.setText("Next");
        option2Button.setText("");
        option3Button.setText("");

    }


    public void theEndGood() {

        playBackgroundMusic("src/music/Shiny Smily Story （Daybreak ver.）.wav");


        location = "Bedroom day 4 (morning), Dialogue 2";


        bgImage = new ImageIcon("src/images/LuminaSquareDay.PNG").getImage();
        backgroundPanel.repaint();
        mainTextPanel.repaint();

        nameplateLabel.setText(" ");
        nameplatePanel.repaint();

        mainTextArea.setText("                  T  H  E    E  N  D");


        mainTextArea.revalidate();
        mainTextArea.repaint();

        option1Button.setText("Play Again");
        option2Button.setText("");
        option3Button.setText("");

    }





    //Bad Ending Route//

    public void badEndingPartOne() {

        playBackgroundMusic("src/music/The Way Home ｜ Zenless Zone Zero OST (Version 1.2).wav");


        location = "Yume Realm day 4, Dialogue 20";


        bgImage = new ImageIcon("src/images/YumeRealm3v2.png").getImage();
        backgroundPanel.repaint();
        mainTextPanel.repaint();

        nameplateLabel.setText("  " + "Yume");
        nameplatePanel.repaint();

        mainTextArea.setText("Yes. It has been a constant struggle between me and the nightmares. I have been able to keep" +
                " it at bay up until recently. But my power, or what’s left of it at least will not be enough to keep up" +
                " the fight for much longer");


        mainTextArea.revalidate();
        mainTextArea.repaint();

        option1Button.setText("Next");
        option2Button.setText("");
        option3Button.setText("");

    }




    public void badEndingPartTwo() {

        location = "Yume Realm day 4, Dialogue 21";


        bgImage = new ImageIcon("src/images/YumeRealm3v2.png").getImage();
        backgroundPanel.repaint();
        mainTextPanel.repaint();

        nameplateLabel.setText("  " + "You");
        nameplatePanel.repaint();

        mainTextArea.setText("Is there anything you can do about it? Any way we can prevent that from happening?");


        mainTextArea.revalidate();
        mainTextArea.repaint();

        option1Button.setText("Next");
        option2Button.setText("");
        option3Button.setText("");

    }


    public void badEndingPartThree() {

        location = "Yume Realm day 4, Dialogue 22";


        bgImage = new ImageIcon("src/images/YumeRealm3v2.png").getImage();
        backgroundPanel.repaint();
        mainTextPanel.repaint();

        nameplateLabel.setText("  " + "Yume");
        nameplatePanel.repaint();

        mainTextArea.setText("Don’t worry. I have foreseen this outcome. So I have prepared something that should seal it" +
                " away for a very long time. It may not be a permanent solution but this should buy you time.");


        mainTextArea.revalidate();
        mainTextArea.repaint();

        option1Button.setText("Next");
        option2Button.setText("");
        option3Button.setText("");

    }


    public void badEndingPartFour() {

        location = "Yume Realm day 4, Dialogue 23";


        bgImage = new ImageIcon("src/images/YumeRealm3v2.png").getImage();
        backgroundPanel.repaint();
        mainTextPanel.repaint();

        nameplateLabel.setText("  " + "You (Internal Monologue)");
        nameplatePanel.repaint();

        mainTextArea.setText("That’s a relief. Though I am not sure what she means by buying me some time, this is better than nothing.");


        mainTextArea.revalidate();
        mainTextArea.repaint();

        option1Button.setText("Next");
        option2Button.setText("");
        option3Button.setText("");

    }


    public void badEndingPartFive() {

        location = "Yume Realm day 4, Dialogue 24";


        bgImage = new ImageIcon("src/images/YumeRealm3v2.png").getImage();
        backgroundPanel.repaint();
        mainTextPanel.repaint();

        nameplateLabel.setText("  " + "You");
        nameplatePanel.repaint();

        mainTextArea.setText("Thank you, Yume.");


        mainTextArea.revalidate();
        mainTextArea.repaint();

        option1Button.setText("Next");
        option2Button.setText("");
        option3Button.setText("");

    }


    public void badEndingPartSix() {

        location = "Yume Realm day 4, Dialogue 25";


        bgImage = new ImageIcon("src/images/YumeRealm3v2.png").getImage();
        backgroundPanel.repaint();
        mainTextPanel.repaint();

        nameplateLabel.setText("  " + "You (Internal Monologue)");
        nameplatePanel.repaint();

        mainTextArea.setText("She looks at me with the usual smile on her face. But this time it had a hint of sadness.");


        mainTextArea.revalidate();
        mainTextArea.repaint();

        option1Button.setText("Next");
        option2Button.setText("");
        option3Button.setText("");

    }


    public void badEndingPartSeven() {

        location = "Yume Realm day 4, Dialogue 26";


        bgImage = new ImageIcon("src/images/YumeRealm3v2.png").getImage();
        backgroundPanel.repaint();
        mainTextPanel.repaint();

        nameplateLabel.setText("  " + "Yume");
        nameplatePanel.repaint();

        mainTextArea.setText("It’s almost time for you to go. Please don’t worry and continue living on as usual. Good bye.");


        mainTextArea.revalidate();
        mainTextArea.repaint();

        option1Button.setText("Next");
        option2Button.setText("");
        option3Button.setText("");

    }


    public void badEndingPartEight() {


        location = "Bedroom Day 4 (morning, bad ending), Dialogue 1";


        bgImage = new ImageIcon("src/images/BedroomDay.png").getImage();
        backgroundPanel.repaint();
        mainTextPanel.repaint();

        nameplateLabel.setText("  " + "You (Internal Monologue)");
        nameplatePanel.repaint();

        mainTextArea.setText("I get woken up by my morning alarm. Another day in my uneventful life. As I get ready to leave" +
                " for work, I keep thinking that there is something I'm forgetting. I can’t put my finger on it but it has been" +
                " in the back of my mind since I came to my senses when I woke up earlier.");


        mainTextArea.revalidate();
        mainTextArea.repaint();

        option1Button.setText("Next");
        option2Button.setText("");
        option3Button.setText("");

    }


    public void badEndingPartNine() {

        location = "Bedroom Day 4 (morning, bad ending), Dialogue 2";


        bgImage = new ImageIcon("src/images/BedroomDay.png").getImage();
        backgroundPanel.repaint();
        mainTextPanel.repaint();

        nameplateLabel.setText("  " + "You (Internal Monologue)");
        nameplatePanel.repaint();

        mainTextArea.setText("Was it the nightmares I’ve been getting? No, it couldn't be that. The reoccurring nightmare " +
                "has been gone for months now. Oh yeah… I never really thought about it until now. I never had that nightmare " +
                "in a while. I wonder what caused this change. ");


        mainTextArea.revalidate();
        mainTextArea.repaint();

        option1Button.setText("Next");
        option2Button.setText("");
        option3Button.setText("");

    }


    public void badEndingPartTen() {

        location = "Bedroom Day 4 (morning, bad ending), Dialogue 3";


        bgImage = new ImageIcon("src/images/LuminaSquareNight.PNG").getImage();
        backgroundPanel.repaint();
        mainTextPanel.repaint();

        nameplateLabel.setText("  ");
        nameplatePanel.repaint();

        mainTextArea.setText("Days, weeks, and months have passed and he continued to live on like nothing ever happened." +
                " The face of a gentle being who saved him from the depths of despair, has faded to oblivion.");


        mainTextArea.revalidate();
        mainTextArea.repaint();

        option1Button.setText("Next");
        option2Button.setText("");
        option3Button.setText("");

    }



    public void theEndBad() {

        playBackgroundMusic("src/music/Shiny Smily Story （Daybreak ver.）.wav");


        location = "Bedroom Day 4 (morning, bad ending), Dialogue 4";


        bgImage = new ImageIcon("src/images/LuminaSquareDay.PNG").getImage();
        backgroundPanel.repaint();
        mainTextPanel.repaint();

        nameplateLabel.setText("  ");
        nameplatePanel.repaint();

        mainTextArea.setText("                  T  H  E    E  N  D");


        mainTextArea.revalidate();
        mainTextArea.repaint();

        option1Button.setText("Play Again");
        option2Button.setText("");
        option3Button.setText("");

    }



    //                                         T  H  E    E  N  D                                          //






    //BACKGROUND MUSIC HANDLER//
    public void playBackgroundMusic(String filePath) {
        try {
            // Stop and close existing clip if playing
            if (backgroundClip != null && backgroundClip.isRunning()) {
                backgroundClip.stop();
                backgroundClip.close();
            }

            File audioFile = new File(filePath);
            if (!audioFile.exists()) {
                System.out.println("Audio file not found: " + audioFile.getAbsolutePath());
                return;
            }

            AudioInputStream audioStream = AudioSystem.getAudioInputStream(audioFile);
            backgroundClip = AudioSystem.getClip();
            backgroundClip.open(audioStream);

            // Setting Audio Volume to 65%
            FloatControl gainControl = (FloatControl) backgroundClip.getControl(FloatControl.Type.MASTER_GAIN);
            float volume = 0.80f;
            float min = gainControl.getMinimum();
            float max = gainControl.getMaximum();
            float dB = min + (max - min) * volume;
            gainControl.setValue(dB);

            backgroundClip.loop(Clip.LOOP_CONTINUOUSLY);
            backgroundClip.start();

        } catch (UnsupportedAudioFileException e) {
            System.out.println("Unsupported audio file: " + filePath);

        } catch (LineUnavailableException e) {
            System.out.println("Audio line unavailable.");

        } catch (IOException e) {
            System.out.println("Error reading audio file: " + filePath);

        } catch (Exception e) {
            System.out.println("An unexpected error occurred while playing audio.");

        }
    }




    //TITLE SCREEN HANDLER//


    public class TitleScreenHandler implements ActionListener {
        public void actionPerformed(ActionEvent event) {
            createGameScreen();
        }
    }


    //OPTION BUTTON LOGIC HANDLER//

    public class OptionButtonHandler implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent event) {

            String optionChoice = event.getActionCommand();

            switch (location) {
                case "Bedroom day 1 (night)":
                    switch (optionChoice) {
                        case "optionOne":
                            chapterOnePartTwo();
                            break;
                        case "optionTwo":
                            break;

                        case "optionThree":
                            break;


                    }
                    break;

                case "Yume Realm Day 1, Dialogue 1":
                    switch (optionChoice) {
                        case "optionOne":
                            chapterOnePartTwoOptionOne();
                            break;
                        case "optionTwo":
                            chapterOnePartTwoOptionTwo();
                            break;
                        case "optionThree":
                            chapterOnePartTwoOptionTwo();
                            break;
                    }
                    break;

                case "Yume Realm Day 1, Dialogue 2":
                    switch (optionChoice) {
                        case "optionOne":
                            chapterOnePartThreeOptionOne();
                            break;
                        case "optionTwo":
                            chapterOnePartThreeOptionTwo();
                            break;
                        case "optionThree":
                            chapterOnePartThreeOptionThree();
                            break;
                    }
                    break;

                case "Yume Realm Day 1, Dialogue 3":
                    switch (optionChoice) {
                        case "optionOne":
                            chapterOnePartTwoOptionOne();
                            break;
                        case "optionTwo":

                            break;
                        case "optionThree":

                            break;
                    }
                    break;

                case "Yume Realm Day 1, Dialogue 4":
                    switch (optionChoice) {
                        case "optionOne":
                            chapterOnePartFour();
                            break;
                        case "optionTwo":

                            break;
                        case "optionThree":

                            break;
                    }
                    break;

                case "Yume Realm Day 1, Dialogue 5":
                    switch (optionChoice) {
                        case "optionOne":
                            chapterOnePartFour();
                            break;
                        case "optionTwo":

                            break;
                        case "optionThree":

                            break;
                    }
                    break;

                case "Yume Realm Day 1, Dialogue 6":
                    switch (optionChoice) {
                        case "optionOne":
                            chapterOnePartFour();
                            break;
                        case "optionTwo":

                            break;
                        case "optionThree":

                            break;
                    }
                    break;

                case "Yume Realm Day 1, Dialogue 7":
                    switch (optionChoice) {
                        case "optionOne":
                            chapterOnePartFive();
                            break;
                        case "optionTwo":

                            break;
                        case "optionThree":

                            break;
                    }
                    break;

                case "Bedroom day 1 (morning), Dialogue 1":
                    switch (optionChoice) {
                        case "optionOne":
                            chapterTwoIntro();
                            break;
                        case "optionTwo":

                            break;
                        case "optionThree":

                            break;
                    }
                    break;

                case "Bedroom day 2 (night), Dialogue 1":
                    switch (optionChoice) {
                        case "optionOne":
                            chapterTwoPartOne();
                            break;
                        case "optionTwo":

                            break;
                        case "optionThree":

                            break;
                    }
                    break;

                case "Bedroom day 2 (night), Dialogue 2":
                    switch (optionChoice) {
                        case "optionOne":
                            chapterTwoPartTwo();
                            break;
                        case "optionTwo":

                            break;
                        case "optionThree":

                            break;
                    }
                    break;


                case "Yume Realm Day 2, Dialogue 1":
                    switch (optionChoice) {
                        case "optionOne":
                            chapterTwoPartThree();
                            break;
                        case "optionTwo":
                            chapterTwoPartThree();
                            break;
                        case "optionThree":

                            break;
                    }
                    break;

                case "Yume Realm Day 2, Dialogue 2":
                    switch (optionChoice) {
                        case "optionOne":
                            chapterTwoPartFour();
                            break;
                        case "optionTwo":

                            break;
                        case "optionThree":

                            break;
                    }
                    break;

                case "Yume Realm Day 2, Dialogue 3":
                    switch (optionChoice) {
                        case "optionOne":
                            chapterTwoPartFiveRouteOne();
                            break;
                        case "optionTwo":
                            chapterTwoPartFiveRouteTwo();
                            break;
                        case "optionThree":
                            chapterTwoPartFiveRouteThree();
                            break;
                    }
                    break;

                case "Yume Realm Day 2, Dialogue 4":
                    switch (optionChoice) {
                        case "optionOne":
                            chapterTwoPartSix();
                            break;
                        case "optionTwo":

                            break;
                        case "optionThree":

                            break;
                    }
                    break;

                case "Yume Realm Day 2, Dialogue 5":
                    switch (optionChoice) {
                        case "optionOne":
                            chapterTwoPartSix();
                            break;
                        case "optionTwo":

                            break;
                        case "optionThree":

                            break;
                    }
                    break;

                case "Yume Realm Day 2, Dialogue 6":
                    switch (optionChoice) {
                        case "optionOne":
                            chapterTwoPartSix();
                            break;
                        case "optionTwo":

                            break;
                        case "optionThree":

                            break;
                    }
                    break;

                case "Yume Realm Day 2, Dialogue 7":
                    switch (optionChoice) {
                        case "optionOne":
                            chapterTwoPartSevenRouteOne();
                            break;
                        case "optionTwo":
                            chapterTwoPartSevenRouteTwo();
                            break;
                        case "optionThree":

                            break;
                    }
                    break;

                case "Yume Realm Day 2, Dialogue 8":
                    switch (optionChoice) {
                        case "optionOne":
                            chapterTwoPartEight();
                            break;
                        case "optionTwo":

                            break;
                        case "optionThree":

                            break;
                    }
                    break;

                case "Yume Realm Day 2, Dialogue 9":
                    switch (optionChoice) {
                        case "optionOne":
                            chapterTwoPartEight();
                            break;
                        case "optionTwo":

                            break;
                        case "optionThree":

                            break;
                    }
                    break;

                case "Bedroom day 2 (morning), Dialogue 1":
                    switch (optionChoice) {
                        case "optionOne":
                            chapterThreeIntro();
                            break;
                        case "optionTwo":

                            break;
                        case "optionThree":

                            break;
                    }
                    break;

                case "Bedroom day 3 (night), Dialogue 1":
                    switch (optionChoice) {
                        case "optionOne":
                                chapterThreePartOne();
                            break;
                        case "optionTwo":

                            break;
                        case "optionThree":

                            break;
                    }
                    break;

                case "Bedroom day 3 (night), Dialogue 2":
                    switch (optionChoice) {
                        case "optionOne":
                            chapterThreePartTwo();
                            
                            break;
                        case "optionTwo":

                            break;
                        case "optionThree":

                            break;
                    }
                    break;

                case "Bedroom day 3 (night), Dialogue 3":
                    switch (optionChoice) {
                        case "optionOne":
                            chapterThreePartThree();

                            break;
                        case "optionTwo":

                            break;
                        case "optionThree":

                            break;
                    }
                    break;

                case "Yume Realm Day 3, Dialogue 1":
                    switch (optionChoice) {
                        case "optionOne":
                            chapterThreePartFour();

                            break;
                        case "optionTwo":

                            break;
                        case "optionThree":

                            break;
                    }
                    break;

                case "Yume Realm Day 3, Dialogue 2":
                    switch (optionChoice) {
                        case "optionOne":
                            chapterThreePartFiveRouteOne();

                            break;
                        case "optionTwo":
                            chapterThreePartFiveRouteTwo();
                            break;
                        case "optionThree":

                            break;
                    }
                    break;

                case "Yume Realm Day 3, Dialogue 3":
                    switch (optionChoice) {
                        case "optionOne":
                            chapterThreePartSix();

                            break;
                        case "optionTwo":

                            break;
                        case "optionThree":

                            break;
                    }
                    break;

                case "Yume Realm Day 3, Dialogue 4":
                    switch (optionChoice) {
                        case "optionOne":
                            chapterThreePartSix();

                            break;
                        case "optionTwo":

                            break;
                        case "optionThree":

                            break;
                    }
                    break;

                case "Yume Realm Day 3, Dialogue 5":
                    switch (optionChoice) {
                        case "optionOne":
                            chapterThreePartSeven();

                            break;
                        case "optionTwo":

                            break;
                        case "optionThree":

                            break;
                    }
                    break;

                case "Yume Realm Day 3, Dialogue 6":
                    switch (optionChoice) {
                        case "optionOne":
                            chapterThreePartEight();

                            break;
                        case "optionTwo":

                            break;
                        case "optionThree":

                            break;
                    }
                    break;

                case "Yume Realm Day 3, Dialogue 7":
                    switch (optionChoice) {
                        case "optionOne":
                            chapterThreePartNine();

                            break;
                        case "optionTwo":

                            break;
                        case "optionThree":

                            break;
                    }
                    break;

                case "Yume Realm Day 3, Dialogue 8":
                    switch (optionChoice) {
                        case "optionOne":
                            chapterThreePartTen();

                            break;
                        case "optionTwo":

                            break;
                        case "optionThree":

                            break;
                    }
                    break;

                case "Bedroom day 3 (morning), Dialogue 1":
                    switch (optionChoice) {
                        case "optionOne":
                            finaleIntro();

                            break;
                        case "optionTwo":

                            break;
                        case "optionThree":

                            break;
                    }
                    break;

                case "Bedroom day 3 (morning), Dialogue 2":
                    switch (optionChoice) {
                        case "optionOne":
                            finaleIntroPartTwo();

                            break;
                        case "optionTwo":

                            break;
                        case "optionThree":

                            break;
                    }
                    break;

                case "Bedroom day 3 (morning), Dialogue 3":
                    switch (optionChoice) {
                        case "optionOne":
                            finalePartOne();

                            break;
                        case "optionTwo":

                            break;
                        case "optionThree":

                            break;
                    }
                    break;

                case "Yume Realm day 4, Dialogue 1":
                    switch (optionChoice) {
                        case "optionOne":
                            finalePartTwo();

                            break;
                        case "optionTwo":

                            break;
                        case "optionThree":

                            break;
                    }
                    break;

                case "Yume Realm day 4, Dialogue 2":
                    switch (optionChoice) {
                        case "optionOne":
                            finalePartThree();

                            break;
                        case "optionTwo":

                            break;
                        case "optionThree":

                            break;
                    }
                    break;

                case "Yume Realm day 4, Dialogue 3":
                    switch (optionChoice) {
                        case "optionOne":
                            finalePartFour();

                            break;
                        case "optionTwo":

                            break;
                        case "optionThree":

                            break;
                    }
                    break;


                //ENDINGS LOGIC//


                case "Yume Realm day 4, Dialogue 4":
                    switch (optionChoice) {
                        case "optionOne":
                            if(affinityValue >= 17){
                                goodEndingPartOne();
                            }
                            else {
                                badEndingPartOne();
                            }

                            break;
                        case "optionTwo":

                            break;
                        case "optionThree":

                            break;
                    }
                    break;




                //Good Ending//

                case "Yume Realm day 4, Dialogue 5":
                    switch (optionChoice) {
                        case "optionOne":
                            goodEndingPartTwo();

                            break;
                        case "optionTwo":

                            break;
                        case "optionThree":

                            break;
                    }
                    break;

                case "Yume Realm day 4, Dialogue 6":
                    switch (optionChoice) {
                        case "optionOne":
                            goodEndingPartThree();

                            break;
                        case "optionTwo":

                            break;
                        case "optionThree":

                            break;
                    }
                    break;

                case "Yume Realm day 4, Dialogue 7":
                    switch (optionChoice) {
                        case "optionOne":
                            goodEndingPartFour();

                            break;
                        case "optionTwo":

                            break;
                        case "optionThree":

                            break;
                    }
                    break;

                case "Yume Realm day 4, Dialogue 8":
                    switch (optionChoice) {
                        case "optionOne":
                            goodEndingPartFive();

                            break;
                        case "optionTwo":

                            break;
                        case "optionThree":

                            break;
                    }
                    break;

                case "Yume Realm day 4, Dialogue 9":
                    switch (optionChoice) {
                        case "optionOne":
                            goodEndingPartSix();

                            break;
                        case "optionTwo":

                            break;
                        case "optionThree":

                            break;
                    }
                    break;

                case "Yume Realm day 4, Dialogue 10":
                    switch (optionChoice) {
                        case "optionOne":
                            goodEndingPartSeven();

                            break;
                        case "optionTwo":

                            break;
                        case "optionThree":

                            break;
                    }
                    break;

                case "Yume Realm day 4, Dialogue 11":
                    switch (optionChoice) {
                        case "optionOne":
                            goodEndingPartEight();

                            break;
                        case "optionTwo":

                            break;
                        case "optionThree":

                            break;
                    }
                    break;

                case "Yume Realm day 4, Dialogue 12":
                    switch (optionChoice) {
                        case "optionOne":
                            goodEndingPartNine();

                            break;
                        case "optionTwo":

                            break;
                        case "optionThree":

                            break;
                    }
                    break;

                case "Yume Realm day 4, Dialogue 13":
                    switch (optionChoice) {
                        case "optionOne":
                            goodEndingPartTen();

                            break;
                        case "optionTwo":

                            break;
                        case "optionThree":

                            break;
                    }
                    break;

                case "Yume Realm day 4, Dialogue 14":
                    switch (optionChoice) {
                        case "optionOne":
                            goodEndingPartEleven();

                            break;
                        case "optionTwo":

                            break;
                        case "optionThree":

                            break;
                    }
                    break;

                case "Yume Realm day 4, Dialogue 15":
                    switch (optionChoice) {
                        case "optionOne":
                            goodEndingPartTwelve();

                            break;
                        case "optionTwo":

                            break;
                        case "optionThree":

                            break;
                    }
                    break;

                case "Yume Realm day 4, Dialogue 16":
                    switch (optionChoice) {
                        case "optionOne":
                            goodEndingPartThirteen();

                            break;
                        case "optionTwo":

                            break;
                        case "optionThree":

                            break;
                    }
                    break;

                case "Yume Realm day 4, Dialogue 17":
                    switch (optionChoice) {
                        case "optionOne":
                            goodEndingPartFourteen();

                            break;
                        case "optionTwo":

                            break;
                        case "optionThree":

                            break;
                    }
                    break;

                case "Yume Realm day 4, Dialogue 18":
                    switch (optionChoice) {
                        case "optionOne":
                            goodEndingPartFifteen();

                            break;
                        case "optionTwo":

                            break;
                        case "optionThree":

                            break;
                    }
                    break;

                case "Yume Realm day 4, Dialogue 19":
                    switch (optionChoice) {
                        case "optionOne":
                            goodEndingPartSixteen();

                            break;
                        case "optionTwo":

                            break;
                        case "optionThree":

                            break;
                    }
                    break;

                case "Bedroom day 4 (morning), Dialogue 1":
                    switch (optionChoice) {
                        case "optionOne":
                            theEndGood();

                            break;
                        case "optionTwo":

                            break;
                        case "optionThree":

                            break;
                    }
                    break;

                case "Bedroom day 4 (morning), Dialogue 2":
                    switch (optionChoice) {
                        case "optionOne":
                            gameStart();

                            break;
                        case "optionTwo":

                            break;
                        case "optionThree":

                            break;
                    }
                    break;


                //Bad Ending//

                case "Yume Realm day 4, Dialogue 20":
                    switch (optionChoice) {
                        case "optionOne":
                            badEndingPartTwo();

                            break;
                        case "optionTwo":

                            break;
                        case "optionThree":

                            break;
                    }
                    break;

                case "Yume Realm day 4, Dialogue 21":
                    switch (optionChoice) {
                        case "optionOne":
                            badEndingPartThree();

                            break;
                        case "optionTwo":

                            break;
                        case "optionThree":

                            break;
                    }
                    break;

                case "Yume Realm day 4, Dialogue 22":
                    switch (optionChoice) {
                        case "optionOne":
                            badEndingPartFour();

                            break;
                        case "optionTwo":

                            break;
                        case "optionThree":

                            break;
                    }
                    break;

                case "Yume Realm day 4, Dialogue 23":
                    switch (optionChoice) {
                        case "optionOne":
                            badEndingPartFive();

                            break;
                        case "optionTwo":

                            break;
                        case "optionThree":

                            break;
                    }
                    break;

                case "Yume Realm day 4, Dialogue 24":
                    switch (optionChoice) {
                        case "optionOne":
                            badEndingPartSix();

                            break;
                        case "optionTwo":

                            break;
                        case "optionThree":

                            break;
                    }
                    break;

                case "Yume Realm day 4, Dialogue 25":
                    switch (optionChoice) {
                        case "optionOne":
                            badEndingPartSeven();

                            break;
                        case "optionTwo":

                            break;
                        case "optionThree":

                            break;
                    }
                    break;

                case "Yume Realm day 4, Dialogue 26":
                    switch (optionChoice) {
                        case "optionOne":
                            badEndingPartEight();

                            break;
                        case "optionTwo":

                            break;
                        case "optionThree":

                            break;
                    }
                    break;

                case "Bedroom Day 4 (morning, bad ending), Dialogue 1":
                    switch (optionChoice) {
                        case "optionOne":
                            badEndingPartNine();

                            break;
                        case "optionTwo":

                            break;
                        case "optionThree":

                            break;
                    }
                    break;

                case "Bedroom Day 4 (morning, bad ending), Dialogue 2":
                    switch (optionChoice) {
                        case "optionOne":
                            badEndingPartTen();

                            break;
                        case "optionTwo":

                            break;
                        case "optionThree":

                            break;
                    }
                    break;

                case "Bedroom Day 4 (morning, bad ending), Dialogue 3":
                    switch (optionChoice) {
                        case "optionOne":
                            theEndBad();

                            break;
                        case "optionTwo":

                            break;
                        case "optionThree":

                            break;
                    }
                    break;


            }

        }
    }
}



//SPECIAL THANKS TO @RyiSnow on YouTube for his tutorials. Without him, I would have never been able to create this project!
//DOMO ARIGATOGOZAIMASU, RyiSnow-san!
//Check his channel out for Java programming/game development tutorials! YOUTUBE CHANNEL: https://www.youtube.com/@RyiSnow