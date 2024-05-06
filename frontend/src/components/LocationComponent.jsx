const LocationComponent = ({location}) => {
    if (!location) {
        return <></>;
    }
    
    return `${location.latitude}\u00B0, ${location.longitude}\u00B0`
}

export default LocationComponent;