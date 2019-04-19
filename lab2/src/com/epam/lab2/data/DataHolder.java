package com.epam.lab2.data;

public final class DataHolder {
    private final String[] filePaths = { "res/main-window/bsuir-image.jpg", "res/main-window/bsu-image.jpg",
            "res/main-window/bspu-image.jpg", "res/main-window/bsptu-image.jpg",
            "res/main-window/bsmu-image.jpg"};

    private final String[] studentsNames = {"Ефим Сироткин", "Андрей Демко", "Никита Гончарук"};

    private final String[] universityNames = {"БГУИР", "БГУ", "БГПУ", "БНТУ", "БГМУ"};

    private final String[] universitySubjects = {"Математика ", "Физика ", "Конструкирование программ ", "Системное программирование ",
            "Архитектура ПК ", "Дискретная математика ", "Теория вероятностей ", "Философия ",
            "Экономика ", "Схемотехника "};

    private  final String[] teachersNames = {"Татьяна Степановна", "Елена Васильевна", "Ирина Викторовна", "Игорь Иванович",
            "Валерий Александрович", "Юрий Васильевич", "Александр Иванович", "Владимир Иосифович",
            "Наталья Александровна", "Сергей Анатольевич"};

    private  final String[] engineersNames = {"Павел Анатольевич", "Елена Васильевна", "Владимир Владимирович", "Александр Викторович",
            "Антон Геннадьевич", "Александра Сергеевна", "Денис Игоревич", "Елена Николаевна",
            "Елена Викторовна", "Владимир Владимирович"};

    public DataHolder() {}

    public String[] getFilePaths() {
        return this.filePaths;
    }
    public String[] getStudentsNames() {
        return this.studentsNames;
    }
    public String[] getUniversityNames() {
        return this.universityNames;
    }
    public String[] getUniversitySubjects(){
        return this.universitySubjects;
    }
    public String[] getTeachersNames() {
        return this.teachersNames;
    }
    public String[] getEngineersNames() {
        return this.engineersNames;
    }
}
