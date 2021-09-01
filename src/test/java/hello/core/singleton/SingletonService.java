package hello.core.singleton;

public class SingletonService {

    //static 선언은 클래스 레벨로 올라가기 때문에 하나만 생성됨
    private static final SingletonService instance = new SingletonService();

    //private 생성자
    private SingletonService(){

    }

    public static SingletonService getInstance(){
        return instance;
    }
    
}
