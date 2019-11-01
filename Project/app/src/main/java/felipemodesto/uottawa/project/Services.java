package felipemodesto.uottawa.project;

public class Services {
    private String _id;
    private String _Servicesname;
    private String  _emploeename;
    private String  _emploeerole;
    private double _price;

    public Services() {
    }
    public Services(String id, String Servicesnames, double price,String emploeename,String emploeerole) {
        _id = id;
        _Servicesname = Servicesnames;
        _price = price;
        _emploeename=emploeename;
        _emploeerole=emploeerole;
    }
    public Services(String Servicesnames, double price,String emploeename,String emploeerole) {
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
    public void setPrice(double price) {
        _price = price;
    }
    public double getPrice() {
        return _price;
    }
}


