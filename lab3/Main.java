package com.epam.lab2;

//import com.sun.glass.events.MouseEvent;

import com.epam.lab2.classes.*;


import com.epam.lab2.data.DataHolder;
import com.epam.lab2.utilities.ButtonCreator;
import com.epam.lab2.utilities.ImageViewCreator;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;


import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Modality;
import javafx.stage.Stage;

//import java.awt.*;
import java.io.File;
import java.util.ArrayList;
import java.util.Optional;


public class Main extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {

        GridPane root = new GridPane();

        root.setPadding(new Insets(20));
        root.setHgap(25);
        root.setVgap(15);
        final DataHolder dataHolder = new DataHolder();
        String[] filePaths = dataHolder.getFilePaths();

        Button[] universityButtons = new Button[filePaths.length];
        String[] universitiesNames = dataHolder.getUniversityNames();
        ImageView[] universityImages = new ImageView[filePaths.length];
        University[] universities = new University[filePaths.length];


        for (int i = 0; i < universityImages.length; ++i) {

            ImageViewCreator sourceImageView = new ImageViewCreator(filePaths[i], 200, 100, false, true);
            ButtonCreator sourceButton = new ButtonCreator(universitiesNames[i], "-fx-background-color: #0a9664; ",
                    Color.WHITE, Font.font("Impact", FontWeight.BOLD, 20),
                    200, 75);

            universityImages[i] = sourceImageView.getImageView();
            universityButtons[i] = sourceButton.getButton();

            universities[i] = new University(universitiesNames[i]);
            root.add(universityImages[i], 0, i, 2, 1);
            root.add(universityButtons[i], 4, i, 2, 1);
        }
        EventHandler<MouseEvent> universityButtonsHandler = new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {


                Button eventButton = (Button) event.getSource();
                String universityName = eventButton.getText();
                University chosenUniversity = new University();

                for (University source : universities) {
                    if (source.isEqualUniversityName(universityName))
                        chosenUniversity = source;
                }
                final University eventSourceUniversity = new University(chosenUniversity);


                Stage newWindow = new Stage();
                String[] studentsNames = dataHolder.getStudentsNames();
                for (String a : studentsNames) {
                    eventSourceUniversity.enrollStudent(a);
                }

                eventSourceUniversity.hireTeachers();
                eventSourceUniversity.hireEngineers();


                for (Student a : eventSourceUniversity.getStudentsList()) {
                    a.getStudentGradeBook().setStudentName(a.getName());
                    a.getStudentGradeBook().setGrades();
                    a.getStudentGradeBook().setGradeBookNumber();
                }

                ButtonCreator sourceButton = new ButtonCreator("Кабинет заведующего кафедрой",
                        "-fx-background-color: #CC0000; ",
                        Color.WHITE,
                        Font.font("Impact", FontWeight.BOLD, 16),
                        400, 75);

                Button headMasterCabinetButton = sourceButton.getButton();

                EventHandler<MouseEvent> headMasterButtonHandler = new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent event) {

                        VBox verticalBoxLayout = new VBox(10);
                        verticalBoxLayout.setPadding(new Insets(10, 10, 10, 10));

                        ButtonCreator sourceButton = new ButtonCreator("Зачислить студента",
                                "-fx-background-color: #FF4500; ",
                                Color.WHITE,
                                Font.font("Impact", FontWeight.BOLD, 16),
                                300, 75);

                        Button enrollStudentButton = sourceButton.getButton();

                        sourceButton = new ButtonCreator("Одобрить экзаменационную сессию",
                                "-fx-background-color: #0a9664; ",
                                Color.WHITE,
                                Font.font("Impact", FontWeight.BOLD, 16),
                                300, 75);

                        Button approveExamSessionButton = sourceButton.getButton();


                        verticalBoxLayout.setAlignment(Pos.TOP_CENTER);
                        verticalBoxLayout.getChildren().addAll(enrollStudentButton, approveExamSessionButton);
                        verticalBoxLayout.setStyle("-fx-background-color: #001d18");

                        Scene headMasterScene = new Scene(verticalBoxLayout, 420, 300);
                        Stage headMasterStage = new Stage();
                        headMasterStage.setScene(headMasterScene);
                        headMasterStage.setTitle("Кабинет заведующего кафедрой");

                        headMasterStage.initModality(Modality.NONE);
                        headMasterStage.initOwner(newWindow);
                        headMasterStage.setX(newWindow.getX() + 100);
                        headMasterStage.setY(newWindow.getY() + 100);
                        headMasterStage.setResizable(false);

                        headMasterStage.show();

                        EventHandler<MouseEvent> enrollStudentHandler = new EventHandler<MouseEvent>() {
                            @Override
                            public void handle(MouseEvent event) {
                                VBox verticalBoxLayout = new VBox(10);
                                HBox horizontalBox = new HBox(50);
                                horizontalBox.setMinWidth(200);
                                Label studentNameLabel = new Label("Имя студента");
                                studentNameLabel.setFont(Font.font("Impact", FontWeight.BOLD, 14));
                                studentNameLabel.setTextFill(Color.WHITE);
                                TextField studentNameTextField = new TextField();
                                horizontalBox.setStyle("-fx-background-color: #0a9664;");
                                horizontalBox.setPadding(new Insets(10, 10, 10, 10));
                                horizontalBox.getChildren().addAll(studentNameLabel, studentNameTextField);

                                ButtonCreator sourceButton = new ButtonCreator("Выдать зачётку",
                                        "-fx-background-color: #0a9664; ",
                                        Color.WHITE,
                                        Font.font("Impact", FontWeight.BOLD, 14),
                                        390, 75);

                                Button giveGradeBookButton = sourceButton.getButton();
                                giveGradeBookButton.setAlignment(Pos.CENTER);

                                EventHandler<MouseEvent> giveGradeBookButtonHandler = new EventHandler<MouseEvent>() {
                                    @Override
                                    public void handle(MouseEvent event) {
                                        Button source = (Button)event.getSource();
                                        String studentName = studentNameTextField.getText();
                                        if (studentName.length() != 0) {
                                            Student newStudent = new Student(studentName);
                                            newStudent.getStudentGradeBook().setGradeBookNumber();
                                            newStudent.getStudentGradeBook().setGrades();
                                            newStudent.getStudentGradeBook().setStudentName(newStudent.getName());
                                            eventSourceUniversity.enrollStudent(newStudent);
                                            source.setText(source.getText()+ " (зачислен)");
                                            source.setDisable(true);
                                        }
                                    }
                                };
                                giveGradeBookButton.addEventHandler(MouseEvent.MOUSE_CLICKED, giveGradeBookButtonHandler);

                                verticalBoxLayout.getChildren().addAll(horizontalBox, giveGradeBookButton);
                                verticalBoxLayout.setStyle("-fx-background-color: #001d18; ");
                                verticalBoxLayout.setPadding(new Insets(10, 10, 10, 10));

                                Scene enrollStudentScene = new Scene(verticalBoxLayout, 400, 100);
                                Stage enrollStudentStage = new Stage();
                                enrollStudentStage.setScene(enrollStudentScene);
                                enrollStudentStage.setTitle("Зачислить студента");

                                enrollStudentStage.initModality(Modality.NONE);
                                enrollStudentStage.initOwner(headMasterStage);
                                enrollStudentStage.setX(headMasterStage.getX() + 100);
                                enrollStudentStage.setY(headMasterStage.getY() + 100);
                                enrollStudentStage.setResizable(false);

                                enrollStudentStage.show();

                            }
                        };
                        enrollStudentButton.addEventHandler(MouseEvent.MOUSE_CLICKED, enrollStudentHandler);

                        EventHandler<MouseEvent> approveExamSessionHandler = new EventHandler<MouseEvent>() {
                            @Override
                            public void handle(MouseEvent event) {
                                Button eventButton = (Button) event.getSource();
                                eventSourceUniversity.closePreveiousExamSession();
                                eventSourceUniversity.approveExamSession();
                                VBox verticalBoxLayout = new VBox(7.5);
                                verticalBoxLayout.setPadding(new Insets(10, 10, 10, 10));
                                verticalBoxLayout.setStyle("-fx-background-color: #001d18");
                                verticalBoxLayout.setAlignment(Pos.TOP_CENTER);
                                int examSessionSize = eventSourceUniversity.getExamsList().size();
                                ArrayList<Exam> examSession = eventSourceUniversity.getExamsList();

                                Label[] examSessionLabel = new Label[examSessionSize];

                                for (int i = 0; i < examSessionLabel.length; ++i) {
                                    examSessionLabel[i] = new Label(examSession.get(i).getExamSubject());
                                    examSessionLabel[i].setStyle("-fx-background-color: #0a9664; ");
                                    examSessionLabel[i].setTextFill(Color.WHITE);
                                    examSessionLabel[i].setPrefSize(400, 35);
                                    examSessionLabel[i].setFont(Font.font("Times New Roman", FontWeight.BOLD, 14));
                                    examSessionLabel[i].setPadding(new Insets(10, 10, 10, 10));
                                    verticalBoxLayout.getChildren().add(examSessionLabel[i]);
                                }

                                Scene examSessionScene = new Scene(verticalBoxLayout, 420, 300);
                                Stage examSessionStage = new Stage();
                                examSessionStage.setScene(examSessionScene);

                                examSessionStage.setTitle("Одобренные предметы");

                                examSessionStage.initModality(Modality.WINDOW_MODAL);
                                examSessionStage.initOwner(headMasterStage);
                                examSessionStage.setX(headMasterStage.getX() + 100);
                                examSessionStage.setY(headMasterStage.getY() + 100);
                                examSessionStage.setResizable(true);

                                examSessionStage.show();
                                ArrayList<Student> universityStudents = eventSourceUniversity.getStudentsList();
                                for(int i = 0; i < universityStudents.size(); ++i){
                                    universityStudents.get(i).setStudentExams(eventSourceUniversity.getExamsList());
                                }
                                String buttonText = eventButton.getText();
                                buttonText += "\n (одобрено) ";
                                eventButton.setText(buttonText);
                                eventButton.setDisable(true);
                            }
                        };
                        approveExamSessionButton.addEventHandler(MouseEvent.MOUSE_CLICKED, approveExamSessionHandler);

                    }
                };
                headMasterCabinetButton.addEventHandler(MouseEvent.MOUSE_CLICKED, headMasterButtonHandler);

                sourceButton = new ButtonCreator("Преподаватели",
                        "-fx-background-color: #0a9664; ",
                        Color.WHITE,
                        Font.font("Impact", FontWeight.BOLD, 16),
                        200, 75);

                Button teachersButton = sourceButton.getButton();

                EventHandler<MouseEvent> teachersHandler = new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent event) {
                        VBox verticalBoxLayout = new VBox(3);
                        verticalBoxLayout.setPadding(new Insets(10, 10, 10, 10));
                        ArrayList<Teacher> teachersArrayList = eventSourceUniversity.getTeachersList();
                        Label[] teachersLabelList = new Label[teachersArrayList.size()];
                        for (int i = 0; i < teachersLabelList.length; ++i) {
                            teachersLabelList[i] = new Label(teachersArrayList.get(i).getTeacherInformation());
                            teachersLabelList[i].setStyle("-fx-background-color: #0a9664; ");
                            teachersLabelList[i].setTextFill(Color.WHITE);
                            teachersLabelList[i].setPrefSize(400, 35);
                            teachersLabelList[i].setFont(Font.font("Times New Roman", FontWeight.BOLD, 14));
                            teachersLabelList[i].setPadding(new Insets(10, 10, 10, 10));
                            verticalBoxLayout.getChildren().add(teachersLabelList[i]);
                        }

                        verticalBoxLayout.setAlignment(Pos.CENTER_LEFT);
                        verticalBoxLayout.setStyle("-fx-background-color: #001d18");

                        Scene teachersScene = new Scene(verticalBoxLayout, 420, 400);
                        Stage teachersStage = new Stage();
                        teachersStage.setScene(teachersScene);
                        teachersStage.setTitle("Teacher list");

                        teachersStage.initModality(Modality.NONE);
                        teachersStage.initOwner(newWindow);
                        teachersStage.setX(newWindow.getX() + 100);
                        teachersStage.setY(newWindow.getY() + 100);
                        teachersStage.setResizable(false);

                        teachersStage.show();

                    }
                };
                teachersButton.addEventHandler(MouseEvent.MOUSE_CLICKED, teachersHandler);

                File file = new File("res/choose-menu-window/teachers-image.jpg");
                String localURL = file.toURI().toString();
                Image teachersImage = new Image(localURL, 150, 75, false, true);
                ImageView teachersImageView = new ImageView(teachersImage);

                HBox teacherHBox = new HBox(50);
                teacherHBox.getChildren().addAll(teachersButton, teachersImageView);

                sourceButton = new ButtonCreator("Студенты",
                        "-fx-background-color: #0a9664; ",
                        Color.WHITE,
                        Font.font("Impact", FontWeight.BOLD, 16),
                        200, 75);
                Button studentsButton = sourceButton.getButton();


                studentsButton.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent studentsButtonEvent) {

                        ArrayList<Student> studentArrayList = eventSourceUniversity.getStudentsList();

                        Button[] studentsListButton = new Button[studentArrayList.size()];


                        VBox vBoxLayout = new VBox(20);
                        vBoxLayout.setPadding(new Insets(20, 10, 10, 20));
                        vBoxLayout.setAlignment(Pos.CENTER);
                        vBoxLayout.setStyle("-fx-background-color: #001d18");

                        int index = 0;
                        for (int i = 0; i < studentsListButton.length; ++i) {
                            studentsListButton[i] = new Button(studentArrayList.get(index).getName());
                            studentsListButton[i].setStyle("-fx-background-color: #0a9664; ");
                            studentsListButton[i].setTextFill(Color.WHITE);
                            studentsListButton[i].setPrefSize(200, 75);
                            studentsListButton[i].setFont(Font.font("Impact", FontWeight.BOLD, 16));
                            index++;
                            vBoxLayout.getChildren().add(studentsListButton[i]);
                        }


                        Scene studentsScene = new Scene(vBoxLayout, 450, 300);


                        Stage studentsWindow = new Stage();
                        studentsWindow.setTitle("Students list");
                        studentsWindow.setScene(studentsScene);

                        studentsWindow.initModality(Modality.NONE);

                        studentsWindow.initOwner(newWindow);

                        studentsWindow.setX(newWindow.getX() - 100);
                        studentsWindow.setY(newWindow.getY() - 100);
                        studentsWindow.setResizable(false);

                        EventHandler<MouseEvent> handler = new EventHandler<MouseEvent>() {
                            public void handle(MouseEvent event) {
                                Stage studentStage = new Stage();
                                HBox studentInfoLayout = new HBox(50);
                                studentInfoLayout.setPadding(new Insets(20, 20, 20, 20));
                                Label infoLabel = new Label();
                                for (Button button : studentsListButton) {
                                    if (event.getSource().equals(button)) {
                                        infoLabel.setText(eventSourceUniversity.getStudentInformation(button.getText()));
                                    }
                                }
                                EventHandler<MouseEvent> passExamHandler = new EventHandler<MouseEvent>() {
                                    public void handle(MouseEvent event) {

                                        String studentName = infoLabel.getText();
                                        int beginning = 0;
                                        int end = studentName.indexOf('\n');
                                        studentName = studentName.substring(beginning, end);
                                        Student student = eventSourceUniversity.findStudent(studentName);

                                        ArrayList<Exam> examList = student.getStudentExams();

                                        if (examList.isEmpty() == true) {
                                            Alert alert = new Alert(Alert.AlertType.WARNING);
                                            alert.setTitle("Внимание!");
                                            alert.setHeaderText("Экзаменационные предметы не одобрены завкафедрой");
                                            alert.setContentText("Обратитесь в кабинет заведующего кафедрой");

                                            alert.showAndWait();
                                            event.consume();
                                        } else {
                                            VBox verticalBoxLayout = new VBox(10);
                                            verticalBoxLayout.setPadding(new Insets(10, 10, 10, 10));
                                            verticalBoxLayout.setAlignment(Pos.CENTER);
                                            verticalBoxLayout.setStyle("-fx-background-color: #001d18");




                                            Button[] examButtons = new Button[examList.size()];

                                            for (int i = 0; i < examButtons.length; ++i) {
                                                ButtonCreator sourceButton = new ButtonCreator(examList.get(i).getExamSubject(),
                                                        "-fx-background-color: #0a9664; ",
                                                        Color.WHITE,
                                                        Font.font("Times New Roman", FontWeight.BOLD, 14),
                                                        250, 75);
                                                examButtons[i] = sourceButton.getButton();
                                                verticalBoxLayout.getChildren().add(examButtons[i]);
                                            }
                                            for(int i = 0; i < examList.size(); ++i)
                                                if(examList.get(i).getExamState() == true)
                                                    examButtons[i].setDisable(true);

                                            EventHandler<MouseEvent> examSubjectHandler = new EventHandler<MouseEvent>() {
                                                @Override
                                                public void handle(MouseEvent event) {
                                                    String studentName = infoLabel.getText();
                                                    String subject = "";
                                                    int beginning = 0;
                                                    int end = studentName.indexOf('\n');
                                                    studentName = studentName.substring(beginning, end);
                                                    Student student = eventSourceUniversity.findStudent(studentName);

                                                    Button source = new Button();
                                                    for (Button button : examButtons) {
                                                        if (event.getSource().equals(button)) {
                                                            source = button;
                                                            subject = (source.getText());
                                                        }
                                                    }

                                                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                                                    alert.setTitle(subject + ", экзаменационная сессия");
                                                    alert.setHeaderText("Результаты таковы:");
                                                    Teacher teacher = eventSourceUniversity.findTeacherBySubject(subject);
                                                    Exam exam = student.findStudentExam(subject);

                                                    alert.setContentText(student.passExam(teacher, exam));
                                                    if(exam.getExamState() == true ) {
                                                        source.setText(subject + ", сдан");
                                                        source.setDisable(true);
                                                    }
                                                    alert.showAndWait();
                                                }

                                            };
                                            for (Button button : examButtons) {
                                                button.addEventHandler(MouseEvent.MOUSE_CLICKED, examSubjectHandler);
                                            }
                                            Scene subjectsScene = new Scene(verticalBoxLayout, 450, 350);
                                            Stage subjectsStage = new Stage();
                                            subjectsStage.setTitle("Список студентов");
                                            subjectsStage.setScene(subjectsScene);

                                            subjectsStage.initModality(Modality.NONE);

                                            subjectsStage.initOwner(studentStage);

                                            subjectsStage.setX(studentStage.getX() - 100);
                                            subjectsStage.setY(studentStage.getY() - 100);
                                            subjectsStage.setResizable(false);

                                            subjectsStage.show();
                                        }

                                    }
                                };


                                infoLabel.setFont(Font.font("Times New Roman", FontWeight.EXTRA_BOLD, 12));
                                infoLabel.setLineSpacing(0.5);
                                infoLabel.setTextFill(Color.WHITE);
                                infoLabel.setStyle("-fx-background-color: #0a9664; ");
                                infoLabel.setPadding(new Insets(10, 10, 10, 10));
                                infoLabel.setWrapText(true);

                                VBox studentActionBox = new VBox(20);
                                Button[] studentActionButtons = new Button[3];

                                studentActionButtons[0] = new Button("Сдать экзамен");
                                studentActionButtons[0].addEventHandler(MouseEvent.MOUSE_CLICKED, passExamHandler);

                                studentActionButtons[1] = new Button("Изменить данные");

                                EventHandler<MouseEvent> modifyHadler = new EventHandler<MouseEvent>() {
                                    @Override
                                    public void handle(MouseEvent event) {
                                        String studentName = infoLabel.getText();
                                        int beginning = 0;
                                        int end = studentName.indexOf('\n');
                                        studentName = studentName.substring(beginning, end);

                                        Button[] modifyButtons = new Button[3];
                                        modifyButtons[0] = new Button("Изменить имя");

                                        EventHandler<MouseEvent> modifyNameHandler = new EventHandler<MouseEvent>() {
                                            @Override
                                            public void handle(MouseEvent event) {

                                                TextInputDialog dialog = new TextInputDialog("Введите новое имя");

                                                dialog.setTitle("Name modifying");
                                                dialog.setHeaderText("Введите имя:");
                                                dialog.setContentText("Имя:");

                                                Optional<String> result = dialog.showAndWait();

                                                result.ifPresent(name -> {

                                                    String studentName = infoLabel.getText();
                                                    int beginning = 0;
                                                    int end = studentName.indexOf('\n');
                                                    studentName = studentName.substring(beginning, end);
                                                    if (!name.equals(dialog.getDefaultValue())) {
                                                        eventSourceUniversity.findStudent(studentName).setStudentName(name);
                                                        eventSourceUniversity.findStudent(name).setGradeBookName(name);
                                                        infoLabel.setText(eventSourceUniversity.getStudentInformation(name));
                                                    }
                                                });


                                            }
                                        };
                                        modifyButtons[0].addEventHandler(MouseEvent.MOUSE_CLICKED, modifyNameHandler);
                                        modifyButtons[1] = new Button("Изменить номер зачётки");

                                        EventHandler<MouseEvent> modifyGradeBookNumberHandler = new EventHandler<MouseEvent>() {
                                            @Override
                                            public void handle(MouseEvent event) {
                                                TextInputDialog dialog = new TextInputDialog("Введите новый номер");

                                                dialog.setTitle("Number modifying");
                                                dialog.setHeaderText("Введите номер:");
                                                dialog.setContentText("Номер:");

                                                Optional<String> result = dialog.showAndWait();

                                                result.ifPresent(name -> {
                                                    Integer newNumber;
                                                    try {
                                                        newNumber = Integer.parseInt(name);
                                                    } catch (NumberFormatException e) {
                                                        newNumber = 0;
                                                    }

                                                    String studentName = infoLabel.getText();
                                                    int beginning = 0;
                                                    int end = studentName.indexOf('\n');
                                                    studentName = studentName.substring(beginning, end);

                                                    eventSourceUniversity.findStudent(studentName).getStudentGradeBook().
                                                            setGradeBookNumber(newNumber);

                                                    infoLabel.setText(eventSourceUniversity.getStudentInformation(studentName));
                                                });
                                            }
                                        };
                                        modifyButtons[1].addEventHandler(MouseEvent.MOUSE_CLICKED, modifyGradeBookNumberHandler);
                                        modifyButtons[2] = new Button("Изменить отметки");


                                        VBox verticalBoxLayout = new VBox(5);
                                        verticalBoxLayout.setPadding(new Insets(10, 10, 10, 10));

                                        for (int i = 0; i < modifyButtons.length; ++i) {
                                            modifyButtons[i].setStyle("-fx-background-color: #0a9664; ");
                                            modifyButtons[i].setTextFill(Color.WHITE);
                                            modifyButtons[i].setPrefSize(250, 75);
                                            modifyButtons[i].setFont(Font.font("Impact", FontWeight.BOLD, 16));
                                            verticalBoxLayout.getChildren().add(modifyButtons[i]);
                                        }
                                        verticalBoxLayout.setAlignment(Pos.CENTER);
                                        verticalBoxLayout.setStyle("-fx-background-color: #001d18");

                                        Scene modifyScene = new Scene(verticalBoxLayout, 500, 300);
                                        Stage modifyStage = new Stage();
                                        modifyStage.setScene(modifyScene);
                                        modifyStage.setTitle("Modify Window");

                                        modifyStage.initModality(Modality.NONE);

                                        modifyStage.initOwner(studentsWindow);
                                        modifyStage.setX(studentsWindow.getX() + 100);
                                        modifyStage.setY(studentsWindow.getY() + 100);
                                        modifyStage.setResizable(false);

                                        modifyStage.show();


                                        EventHandler<MouseEvent> modifyGrades = new EventHandler<MouseEvent>() {
                                            @Override
                                            public void handle(MouseEvent event) {

                                                String studentName = infoLabel.getText();
                                                int beginning = 0;
                                                int end = studentName.indexOf('\n');
                                                studentName = studentName.substring(beginning, end);
                                                String[] subjects = eventSourceUniversity.findStudent(studentName).
                                                        getStudentGradeBook().getSubjects();

                                                HBox[] horizontalBox = new HBox[subjects.length];
                                                TextField[] textFields = new TextField[subjects.length];
                                                Label[] subjectLabels = new Label[subjects.length];

                                                VBox verticalBoxLayout = new VBox(5);
                                                verticalBoxLayout.setPadding(new Insets(5, 5, 5, 5));
                                                verticalBoxLayout.setStyle("-fx-background-color: #001d18; ");

                                                Button ApplyButton = new Button("Применить изменения");
                                                ApplyButton.setStyle("-fx-background-color: #FF4500; ");
                                                ApplyButton.setFont(Font.font("Impact", FontWeight.BOLD, 16));
                                                ApplyButton.setTextFill(Color.WHITE);

                                                for (int i = 0; i < horizontalBox.length; ++i) {
                                                    textFields[i] = new TextField();
                                                    textFields[i].setMinWidth(50);
                                                    subjectLabels[i] = new Label(subjects[i]);
                                                    subjectLabels[i].setFont(Font.font("Times New Roman", FontWeight.EXTRA_BOLD, 16));
                                                    subjectLabels[i].setTextFill(Color.WHITE);
                                                    subjectLabels[i].setStyle("-fx-background-color: #0a9664; ");
                                                    subjectLabels[i].setPadding(new Insets(10, 10, 10, 10));
                                                    subjectLabels[i].setMinWidth(250);
                                                    subjectLabels[i].setWrapText(true);
                                                    horizontalBox[i] = new HBox(50);
                                                    horizontalBox[i].setPadding(new Insets(10, 10, 10, 10));
                                                    horizontalBox[i].setStyle("-fx-background-color: #0a9664; ");
                                                    horizontalBox[i].getChildren().addAll(subjectLabels[i], textFields[i]);
                                                    verticalBoxLayout.getChildren().add(horizontalBox[i]);
                                                }
                                                verticalBoxLayout.getChildren().add(ApplyButton);
                                                verticalBoxLayout.setPrefHeight(20);
                                                ApplyButton.setMinWidth(490);

                                                ApplyButton.setOnAction(new EventHandler<ActionEvent>() {
                                                    @Override
                                                    public void handle(ActionEvent event) {

                                                        String studentName = infoLabel.getText();
                                                        int beginning = 0;
                                                        int end = studentName.indexOf('\n');
                                                        studentName = studentName.substring(beginning, end);
                                                        Student student = eventSourceUniversity.findStudent(studentName);
                                                        GradeBook studentGradeBook = student.getStudentGradeBook();

                                                        for (int i = 0; i < textFields.length; ++i) {
                                                            int newGrade;
                                                            try {
                                                                newGrade = Integer.parseInt(textFields[i].getText());

                                                            } catch (NumberFormatException exc) {
                                                                System.out.println("Exception: incorrect grade input");
                                                                newGrade = student.getStudentGradeBook().getSubjectGrade(subjects[i]);
                                                            }
                                                            studentGradeBook.setSubjectGrade(subjects[i], newGrade);
                                                        }
                                                    }
                                                });

                                                Scene modifyGradesScene = new Scene(verticalBoxLayout, 500, 680);
                                                Stage modifyGradesStage = new Stage();
                                                modifyGradesStage.setScene(modifyGradesScene);

                                                modifyGradesStage.setTitle("Modify Window");

                                                modifyGradesStage.initModality(Modality.NONE);

                                                modifyGradesStage.initOwner(modifyStage);
                                                modifyGradesStage.setX(modifyStage.getX() + 100);
                                                modifyGradesStage.setY(modifyStage.getY() - 120);
                                                modifyGradesStage.setResizable(false);

                                                modifyGradesStage.show();

                                            }
                                        };
                                        modifyButtons[2].addEventHandler(MouseEvent.MOUSE_CLICKED, modifyGrades);
                                    }
                                };
                                studentActionButtons[1].addEventHandler(MouseEvent.MOUSE_CLICKED, modifyHadler);

                                studentActionButtons[2] = new Button("Обновить данные");


                                EventHandler<MouseEvent> updateHandler = new EventHandler<MouseEvent>() {
                                    @Override
                                    public void handle(MouseEvent event) {

                                        String studentName = infoLabel.getText();
                                        int beginning = 0;
                                        int end = studentName.indexOf('\n');
                                        studentName = studentName.substring(beginning, end);

                                        infoLabel.setText(eventSourceUniversity.findStudent(studentName).getStudentInformation());
                                    }
                                };
                                studentActionButtons[2].addEventHandler(MouseEvent.MOUSE_CLICKED, updateHandler);


                                for (int i = 0; i < studentActionButtons.length; ++i) {
                                    studentActionButtons[i].setStyle("-fx-background-color: #0a9664; ");
                                    studentActionButtons[i].setTextFill(Color.WHITE);
                                    studentActionButtons[i].setPrefSize(200, 40);
                                    studentActionButtons[i].setFont(Font.font("Impact", FontWeight.BOLD, 16));
                                    studentActionBox.getChildren().add(studentActionButtons[i]);
                                }
                                studentActionButtons[2].setStyle("-fx-background-color: #FF4500; ");

                                File file = new File("res/maintain-window/maintain-image.jpg");
                                String localURL = file.toURI().toString();
                                Image logoImage = new Image(localURL, 200, 150, false, true);
                                ImageView logoImageView = new ImageView(logoImage);
                                studentActionBox.getChildren().add(logoImageView);

                                studentInfoLayout.getChildren().add(infoLabel);
                                studentInfoLayout.getChildren().add(studentActionBox);
                                studentInfoLayout.setStyle("-fx-background-color: #001d18");

                                Scene studentScene = new Scene(studentInfoLayout, 500, 350);

                                studentStage.setTitle("Student maintain");

                                studentStage.setScene(studentScene);

                                studentStage.initModality(Modality.NONE);

                                studentStage.initOwner(studentsWindow);
                                studentStage.setX(studentsWindow.getX() + 100);
                                studentStage.setY(studentsWindow.getY() + 100);
                                studentStage.setResizable(false);

                                studentStage.show();
                                event.consume();
                            }
                        };
                        for (Button button : studentsListButton) {
                            button.addEventHandler(MouseEvent.MOUSE_CLICKED, handler);
                        }

                        studentsWindow.show();
                        studentsButtonEvent.consume();

                    }

                });

                file = new File("res/choose-menu-window/students-image.jpg");
                localURL = file.toURI().toString();
                Image studentsImage = new Image(localURL, 150, 75, false, true);
                ImageView studentsImageView = new ImageView(studentsImage);

                HBox studentsHBox = new HBox(50);
                studentsHBox.getChildren().addAll(studentsButton, studentsImageView);

                Button engineerButton = new Button("Инженеры");
                engineerButton.setStyle("-fx-background-color: #0a9664; ");
                engineerButton.setTextFill(Color.WHITE);
                engineerButton.setPrefSize(200, 75);
                engineerButton.setFont(Font.font("Impact", FontWeight.BOLD, 16));

                EventHandler<MouseEvent> engineersListHandler = new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent event) {
                        VBox verticalBoxLayout = new VBox(5);
                        verticalBoxLayout.setPadding(new Insets(10, 10, 10, 10));

                        ArrayList<Engineer> engineerArrayList = eventSourceUniversity.getEngineersList();
                        Label[] engineersLabel = new Label[engineerArrayList.size()];

                        for (int i = 0; i < engineersLabel.length; ++i) {
                            engineersLabel[i] = new Label(engineerArrayList.get(i).getEngineerInformation());
                            engineersLabel[i].setStyle("-fx-background-color: #0a9664; ");
                            engineersLabel[i].setTextFill(Color.WHITE);
                            engineersLabel[i].setPrefSize(400, 35);
                            engineersLabel[i].setFont(Font.font("Times New Roman", FontWeight.BOLD, 14));
                            engineersLabel[i].setPadding(new Insets(10, 10, 10, 10));
                            verticalBoxLayout.getChildren().add(engineersLabel[i]);
                        }

                        verticalBoxLayout.setAlignment(Pos.CENTER_LEFT);
                        verticalBoxLayout.setStyle("-fx-background-color: #001d18");

                        Scene engineersScene = new Scene(verticalBoxLayout, 420, 400);
                        Stage engineersStage = new Stage();
                        engineersStage.setScene(engineersScene);
                        engineersStage.setTitle("Teacher list");

                        engineersStage.initModality(Modality.NONE);
                        engineersStage.initOwner(newWindow);
                        engineersStage.setX(newWindow.getX() + 100);
                        engineersStage.setY(newWindow.getY() + 100);
                        engineersStage.setResizable(false);

                        engineersStage.show();

                    }
                };
                engineerButton.addEventHandler(MouseEvent.MOUSE_CLICKED, engineersListHandler);

                file = new File("res/choose-menu-window/engineers-image.jpg");
                localURL = file.toURI().toString();
                Image engineerImage = new Image(localURL, 150, 75, false, true);
                ImageView engineerImageView = new ImageView(engineerImage);


                HBox engineerHBox = new HBox(50);
                engineerHBox.getChildren().addAll(engineerButton, engineerImageView);


                VBox verticalBox = new VBox(20);
                verticalBox.setPadding(new Insets(20, 10, 10, 20));
                verticalBox.getChildren().addAll(headMasterCabinetButton, studentsHBox, teacherHBox, engineerHBox);
                verticalBox.setAlignment(Pos.CENTER);
                verticalBox.setStyle("-fx-background-color: #001d18");


                Scene secondScene = new Scene(verticalBox, 450, 350);


                newWindow.setTitle("Choose menu");
                newWindow.setScene(secondScene);


                newWindow.initModality(Modality.NONE);
                newWindow.initOwner(primaryStage);
                newWindow.setX(primaryStage.getX() + 100);
                newWindow.setY(primaryStage.getY() + 100);
                newWindow.setResizable(false);

                newWindow.show();

            }
        };
        for (Button button : universityButtons) {
            button.addEventHandler(MouseEvent.MOUSE_CLICKED, universityButtonsHandler);
        }
        root.setStyle("-fx-background-color: #001d18");
        Scene scene = new Scene(root, 400, 600);
        primaryStage.setResizable(false);
        primaryStage.setTitle("University Galaxy");
        primaryStage.setScene(scene);
        primaryStage.show();

    }

    public static void main(String[] args) {
        // write your code here

        launch(args);
    }
}
