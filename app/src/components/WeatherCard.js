import React from "react";
import PropTypes from "prop-types";
import { withStyles } from "@material-ui/core/styles";
import Card from "@material-ui/core/Card";
import CardActions from "@material-ui/core/CardActions";
import CardContent from "@material-ui/core/CardContent";
import CardMedia from "@material-ui/core/CardMedia";
import Button from "@material-ui/core/Button";
import Typography from "@material-ui/core/Typography";
import { Divider } from "@material-ui/core";

const styles = {
  card: {},
  media: {
    objectFit: "cover"
  }
};

const images = {
  clearSky:
    "https://media.istockphoto.com/photos/sun-on-blue-sky-picture-id531170610?k=6&m=531170610&s=612x612&w=0&h=RFNVp3NP1fZi6MVijkwgljxAjAvlWu1s5cbmB89EZMY=",
  fewClouds:
    "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQHcZ_7nxvGwz89YNG5hrHnFw6Ytjh7SsdG2E8279ggUXAOpXwh",
  brokenClouds:
    "https://img00.deviantart.net/6364/i/2017/030/4/1/broken_clouds_by_kevintheman-dax9bd4.jpg",
  showerRain:
    "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQYcehwr8JiVppVPGebobiqV_FAUVVAIXST2XBvmPe25sSWertW",
  rain:
    "https://media.mnn.com/assets/images/2017/03/raindrops-plants-smell.jpg.653x0_q80_crop-smart.jpg",
  thunderstorm:
    "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRByQHGIODV6VChFYVIby7rd32KLtiTQzabjC3xT4poAGPKqW1V",
  snow:
    "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQxRokhkGX7lXsKKQQcAd4nTWDaA5TJ2tqGBNuvWwaToHpReyhW5Q",
  mist:
    "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQ23ueNdlZ3TYam-Y34Bf7Cy6jOAl9fixE-ZJCqkwb1DXeY4Vtp"
};

function WeatherCard(props) {
  function getWeatherPic() {
    switch (props.icon) {
      case "01d":
        return images.clearSky;
      case "01n":
        return images.clearSky;
      case "02d":
        return images.fewClouds;
      case "02n":
        return images.fewClouds;
      case "03d":
        return images.fewClouds;
      case "03n":
        return images.fewClouds;
      case "04":
        return images.brokenClouds;
      case "04n":
        return images.brokenClouds;
      case "09d":
        return images.showerRain;
      case "09n":
        return images.showerRain;
      case "010d":
        return images.rain;
      case "010n":
        return images.rain;
      case "11d":
        return images.thunderstorm;
      case "11n":
        return images.thunderstorm;
      case "13d":
        return images.snow;
      case "13n":
        return images.snow;
      case "50d":
        return images.mist;
      case "50n":
        return images.mist;
      default:
        return images.fewClouds;
    }
  }
  const { classes } = props;
  return (
    <Card className={classes.card}>
      <CardMedia
        component="img"
        height="140"
        className={classes.media}
        image={getWeatherPic()}
        title="Weather"
      />
      <CardContent style={{ minHeight: "20vh" }}>
        <Typography gutterBottom variant="headline" component="h2">
          Weather - {props.description}
          <div styles={{ height: "auto", width: "auto" }}>
            <img src={`http://openweathermap.org/img/w/${props.icon}.png`} />
          </div>
        </Typography>
        <Typography component="p">
          wind: {props.wind}
          <Divider />
          temperature: {props.temperature}
          <Divider />
          humidity: {props.humidity}
        </Typography>
      </CardContent>
    </Card>
  );
}

WeatherCard.propTypes = {
  classes: PropTypes.object.isRequired
};

export default withStyles(styles)(WeatherCard);
