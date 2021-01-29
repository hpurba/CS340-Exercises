//1. What design principles does this code violate?
//  Simplicity and code duplication
//2. Refactor the code to improve its design.


public in getInterval(Properties props){
        String valueString = props.getProperty("interval");
        if (valueString == null) {
            throw new MissingPropertiesException("monitor interval");
        }
        int value = Integer.parseInt(valueString);
        if (value <= 0) {
            throw new MissingPropertiesException("monitor interval > 0");
        }
        return value;
}

public in getDuration(Properties props){
        String valueString = props.getProperty("duration");
        if (valueString == null) {
            throw new MissingPropertiesException("duration");
        }

        int value = Integer.parseInt(valueString);
        if (value <= 0) {
            throw new MissingPropertiesException("duration > 0");
        }
        if ((value % checkInterval) != 0) {
            throw new MissingPropertiesException("duration % checkInterval");
        }
        return value;
}

public int getDepartureOffset(Properties props){
        String valueString = props.getProperty("departure");
        if (valueString == null) {
            throw new MissingPropertiesException("departure offset");
        }
        int value = Integer.parseInt(valueString);
        if (value <= 0) {
            throw new MissingPropertiesException("departure > 0");
        }
        if ((value % checkInterval) != 0) {
            throw new MissingPropertiesException("departure % checkInterval");
        }
        return value;
        }

public void getTimes(Properties props) throws Exception {
        checkInterval = getInterval(props);
        monitorTime = getDuration(props);
        departureOffset = getDepartureOffset(props);
}

