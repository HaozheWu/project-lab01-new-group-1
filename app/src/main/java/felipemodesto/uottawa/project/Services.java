package felipemodesto.uottawa.project;

class Services {
    private String _id;
    private String _Servicesname;
    private String  _emploeename;
    private String  _emploeerole;
    private String _price;
    private String _publicid;
    public Services() {
    }
    public Services(String id, String Servicesnames,String  price,String emploeename,String emploeerole,String _publici) {
        _id = id;
        _Servicesname = Servicesnames;
        _price = price;
        _emploeename=emploeename;
        _emploeerole=emploeerole;
        _publicid=_publici;
    }
    public Services(String id, String Servicesnames,String  price,String emploeename,String emploeerole) {
        _id = id;
        _Servicesname = Servicesnames;
        _price = price;
        _emploeename=emploeename;
        _emploeerole=emploeerole;
    }
    public Services(String Servicesnames, String price,String emploeename,String emploeerole) {
        _Servicesname = Servicesnames;
        _price = price;
        _emploeename=emploeename;
        _emploeerole=emploeerole;
    }

    public String get_emploeename() {
        return _emploeename;
    }

    public void set_emploeerole(String _emploeerole) {
        this._emploeerole = _emploeerole;
    }

    public void set_emploeename(String _emploeename) {
        this._emploeename = _emploeename;
    }

    public String get_emploeerole() {
        return _emploeerole;
    }

    public void setId(String id) {
        _id = id;
    }
    public String getId() {
        return _id;
    }
    public void setServicesName(String Servicenames) {
        _Servicesname = Servicenames;
    }
    public String getServicesName() {
        return _Servicesname;
    }
    public void setPrice( String price) {
        _price = price;
    }
    public String  getPrice() {
        return _price;
    }

    public void set_publicid(String _publicid) {
        this._publicid = _publicid;
    }

    public String get_publicid() {
        return _publicid;
    }
}


