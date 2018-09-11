package com.example.elmo7tram.dssproject2;



public class User {
    int _id;
    String _name;
    String _email;
    String _password;
    String _address;
    String _phone;
    int _status;

    public User(){

    }

    public User(int id, String name, String email, String password, String address, String phone, int status) {
        this._id=id;
        this._name=name;
        this._email=email;
        this._password=password;
        this._address=address;
        this._phone=phone;
        this._status=status;

    }
    public User(String name, String email, String password, String address, String phone, int status) {
        this._name=name;
        this._email=email;
        this._password=password;
        this._address=address;
        this._phone=phone;
        this._status=status;

    }
    public int get_id(){return _id;}
    public String get_name(){return _name;}
    public String get_email(){return _email;}
    public String get_password(){return _password;}
    public String  get_address(){return _address;}
    public String get_phone(){return _phone;}
    public int get_status(){return _status;}

    public void set_id(int _id){
        this._id=_id;
    }
    public void set_name(String _name){
        this._name=_name;
    }
    public void set_email(String _email){
        this._email=_email;
    }
    public void set_password(String _password){
        this._password=_password;
    }
    public void set_address(String _address){
        this._address=_address;
    }
    public void set_phone(String _phone){
        this._phone=_phone;
    }
    public void set_status(int _status){
        this._status=_status;
    }


}
