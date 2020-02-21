package me.bucklb.auditDemo.Domain;

/*
    Represent a CARF Event Detail (as best I recall it)
 */
public class CarfEventDetail {
    private String name;
    private String b4;
    private String af;
    private String rf;

    // The one thing we absolutely want is the name
    private CarfEventDetail(String name, String b4, String af, String rf) {
        this.name = name;
        this.b4 = b4;
        this.af = af;
        this.rf = rf;
    }


    // The one thing we absolutely want is the name
    public CarfEventDetail(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getB4() {
        return b4;
    }

    public void setB4(String b4) {
        this.b4 = b4;
    }

    public String getAf() {
        return af;
    }

    public void setAf(String af) {
        this.af = af;
    }

    public String getRf() {
        return rf;
    }

    public void setRf(String rf) {
        this.rf = rf;
    }

    @Override
    public String toString() {
        return "CarfEventDetail{" +
                "name='" + name + '\'' +
                ", b4='" + b4 + '\'' +
                ", af='" + af + '\'' +
                ", rf='" + rf + '\'' +
                '}';
    }

        // Play with builder ?
        public static class CarfEventDetailBuilder {
            private String name;
            private String b4;
            private String af;
            private String rf;

            // Not going to play if we don't get a name
            public CarfEventDetailBuilder(String name){
                // Check we got a meaningful name !!!!
                this.name = name;
            }

            public CarfEventDetailBuilder setB4(String b4) {
                this.b4 = b4;
                return this;
            }
            public CarfEventDetailBuilder setAf(String af) {
                this.af = af;
                return this;
            }
            public CarfEventDetailBuilder setRf(String rf) {
                this.rf = rf;
                return this;
            }

            public CarfEventDetail build() {
                /*
                    TODO : validation
                    Check that we have a name AND
                    an "RF" value XOR (a "B4" value OR an "AF" value).
                    Cannot have RF and AF as never search and add/amend at the same time !!
                 */
                return new CarfEventDetail(name,b4,af,rf);
            }


        }





}
