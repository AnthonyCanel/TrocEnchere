package fr.eni.bll;

public class BusinessException extends Exception{
    //renvoyer le msg
    public BusinessException(String message) {
        super(message);
    }
}
