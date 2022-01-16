package com.abhi.quiz.db;
/*
Created by Abhishek on 7/11/2019
*/

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.abhi.quiz.model.Question;
import java.util.ArrayList;
import java.util.List;

public class DbOperations extends SQLiteOpenHelper {

    public DbOperations(Context context) {
        super(context, "quiz.db" , null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS scores(name VARCHAR, score NUMBER)");
        sqLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS questions(id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, question VARCHAR, optionA VARCHAR," +
                "optionB VARCHAR, optionC VARCHAR,optionD VARCHAR,answer NUMBER)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS questions");
        db.execSQL("DROP TABLE IF EXISTS scores");
        onCreate(db);
    }
    public void insertDB()
    {
        //tv.setText("Getting the quiz ready...");
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();

        List<Question> questionList = getQuizData();
        for(int i=0;i<questionList.size();i++)
        {
            String query="INSERT INTO questions(question,optionA,optionB,optionC,optionD,answer) values('" + questionList.get(i).getQuestion()
                    + "','" + questionList.get(i).getOptionA()
                    + "','" + questionList.get(i).getOptionB()
                    + "','" + questionList.get(i).getOptionC()
                    + "','" + questionList.get(i).getOptionD()
                    + "','" + questionList.get(i).getAnswer()+"');)";
            sqLiteDatabase.execSQL(query);
        }
    }

    public Cursor selectQuestionsQuery() {
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        return sqLiteDatabase.rawQuery("SELECT * FROM questions", null);

    }

    public Cursor selectScoresQuery() {
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        return sqLiteDatabase.rawQuery("SELECT * FROM scores", null);
    }

    public void insertNewHighScore(String userName, int score) {
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        String query="INSERT INTO scores VALUES('" + userName + "','" + score + "')";
        sqLiteDatabase.execSQL(query);
    }

    public void updateNewHighScore(String userName, int newScore, String oldScore) {
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        String query="UPDATE scores SET name='"+ userName +"', score='"+ newScore +"' where score='"+ oldScore +"'";
        sqLiteDatabase.execSQL(query);
    }


    private List<Question> getQuizData() {

        List<Question> questionList = new ArrayList<>();

        questionList.add(new Question("A computer virus is a", "Hardware", "Software", "Bacteria", "Freeware", 2));
        questionList.add(new Question("Who is known by the name of crackers in the context of computer security?", "Black Hat Hackers", "White Hat Hackers", "Elite Hackers", "Script Kiddie", 1));
        questionList.add(new Question("In computing, ________ is a network security system that monitors and controls incoming and outgoing network traffic based on predetermined security rules.", "Spyware", "Cookie", "Spam", "Firewall", 4));
        questionList.add(new Question("A layer-4 firewall (a device that can look at all protocol headers up to the transport layer) CANNOT", "block entire HTTP traffic during 9:00 PM and 5:00 AM", "block all ICMP traffic", "stop incoming traffic from a specific IP address but allow outgoing traffic to the same IP address", "block TCP traffic from a specific user on a multi-user system during 9:00 PM and 5:00 AM", 4));
        questionList.add(new Question("_____ uses pretty good privacy algorithm.", "Electronic mails", "File encryption", "Both Electronic mails and File encryption", "None of the options", 3));
        questionList.add(new Question("A digital signature is required:", "for non-repudiation of communication by a sender", "for all e-mail sending", "for all DHCP server", "for FTP transactions", 1));
        questionList.add(new Question("In a columnar transposition cipher, the plain text is “the tomato is a plant in the night shade family”, keyword is “TOMATO”. The cipher text is", "TINESAX / EOAHTFX / HTLTHEY / MAILAIX / TAPNGDL / OSTNHMX", "TINESAX / EOAHTEX / MAILAIX / HTLTHEY / TAPNGDL / OSTNHMX", "TINESAX / EOAHTFX / HTLTHEY / MAILAIX / OSTNHMX / TAPNGDL", "EOAHTFX / TINESAX / HTLTHEY / MAILAIX / TAPNGDL / OSTNHMX", 1));
        questionList.add(new Question("Suppose that everyone in a group on N people wants to communicate secretly with the (N - 1) others using symmetric Key cryptographic system. The communication between any two person should not be decodable by the others in the group. The number of keys required in the system as a whole to satisfy the confidentiality requirement is", "2N", "N(N - 1)", "N(N - 1)/2", "(N - 1)2", 3));
        questionList.add(new Question("What does DDoS stand for?", "Data Denial-of-Service", "Distributed Denial-of-Service", "Distributed Data of Server", "Distribution of Data Service", 2));
        questionList.add(new Question("What part of 192.168.10.51 is the Network ID, assuming a default subnet mask?", "192", "192.168", "192.168.10", "192.168.10.51", 3));
        questionList.add(new Question("Consider the following statement.\n" +
                "\n" +
                "I. Packet Filter Firewall analyze network traffic at transport layer.\n" +
                "\n" +
                "II. Circuit Level Firewall operate at transport and session layer of OSI Model.\n" +
                "\n" +
                "From the above statement which statement/s is/are True", "None", "Only II", "I and II", "Only I", 2));
        questionList.add(new Question("In an RSA cryptosystem, a participant A uses two prime numbers p = 13 and q = 17 to generate her public and private keys. If the public key of A is 35, then the private key of A is ______.", "11", "14", "15", "21", 1));
        questionList.add(new Question("In an RSA cryptosystem, the value of the public modulus parameter n is 3007. If it is also known that φ(n) = 2880, where φ() denotes Euler’s Totient Function, then the prime factor of n which is greater than 50 is ________.", "67", "99", "97", "102", 3));
        questionList.add(new Question("Which of the following is a malicious software that, on execution, runs its own code and modifies other computer programs?", "Virus", "Spam", "Spyware", "Adware", 1));
        questionList.add(new Question("In cryptography, the following uses transposition ciphers and the keyword is LAYER. Encrypt the following message. (Spaces are omitted during encryption)\n" +
                "\n" +
                "WELCOME TO NETWORK SECURITY !", "WMEKREETSILTWETCOOCYONRU!", "EETSICOOCYWMEKRONRU!LTWET", "LTWETONRU!WMEKRCOOCYEETSI", "ONRU!COOCYLTWETEETSIWMEKR", 2));
        questionList.add(new Question("In RSA\n" +
                "\n" +
                "1. p and q are chosen as very large prime number\n" +
                "\n" +
                "2. Compute n = p * q and ϕ(n) = (p-1) * (q-1)\n" +
                "\n" +
                "Later e encryption key chosen. How this encryption key is selected?", "e is chosen as a relative prime to ϕ(n)", "e is chosen as a relative prime to q", "e is chosen as a relative prime to n", "e is chosen as a relative prime to p", 1));
        questionList.add(new Question("The value of 351 mod 5 is _________.", "1", "2", "3", "4", 2));
        questionList.add(new Question("Given below are two statements: One is labeled as Assertion (A) and the other is labeled as Reason (R) :\n" +
                "\n" +
                "Statement I: In Caesar Cipher each letter of Plain text is replaced by another letter for encryption. \n" +
                "\n" +
                "Statement II: Diffie-Hellman algorithm is used for exchange of secret key. \n" +
                "\n" +
                "In the light of the above statements, choose the correct answer from the options given below:", "Both Statement I and Statement II are true", "Both Statement I and Statement II are false", "Statement I is correct but Statement II is false", "Statement I is incorrect but Statement II is true", 1));
        questionList.add(new Question("TCP port Number for http services is", "21", "22", "80", "88", 3));
        questionList.add(new Question("A sender S sends a message m to receiver R, which is digitally signed by S with its private key. In this scenario. One or more of the following security violations can take place.\n" +
                "\n" +
                "1) S can launch a birthday attack to replace m with a fraudulent message.\n" +
                "\n" +
                "2) A third-party attacker can launch a birthday attack to replace m with a fraudulent message. \n" +
                "\n" +
                "3) R can launch a birthday attack to replace m with a fraudulent message.\n" +
                "\n" +
                "Which of the following are possible security violations?", "1 and 2 only", "1 only", "2 only", "2 and 3 only", 1));

        return questionList;
    }


}
