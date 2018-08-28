import React, { Component } from "react";
import PropTypes from "prop-types";
import { withStyles } from "@material-ui/core/styles";
import Card from "@material-ui/core/Card";
import CardContent from "@material-ui/core/CardContent";
import CardMedia from "@material-ui/core/CardMedia";
import Typography from "@material-ui/core/Typography";
import { Divider, FormControl, InputLabel, Select } from "@material-ui/core";
import { IconButton } from "@material-ui/core";
import HistoryIcon from "@material-ui/icons/History";
import "../css/WeatherTileImage.css";

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

const cities = [
  { name: "London", code: "London,uk" },
  { name: "Strasbourg", code: "Strasbourg,fr" },
  { name: "Sydney", code: "Sydney,au" },
  { name: "Seychelles", code: "Republic of Seychelles,sc" },
  { name: "Toronto", code: "Toronto,ca" },
  { name: "Tokyo", code: "Tokyo,jp" },
  { name: "San Francisco", code: "San Francisco,us" },
  { name: "Reykjavik", code: "Reykjavik,is" },
  { name: "Dubai", code: "Dubai,ae" }
];

class WeatherCard extends Component {
  state = {
    city: ""
  };

  getWeatherPic() {
    switch (this.props.icon) {
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

  handleCityChange = name => event => {
    this.setState({ [name]: event.target.value });
    this.props.selectCity(String(event.target.value));
  };
  render() {
    return (
      <Card>
        <CardMedia
          component="img"
          height="140"
          image={this.getWeatherPic()}
          title="Weather"
        />
        <CardContent style={{ minHeight: "20vh" }}>
          <Typography gutterBottom variant="headline" component="h2">
            <div
              style={{
                display: "flex",
                width: "100%",
                justifyContent: "space-between",
                backgroundSize: "cover"
              }}
            >
              {" "}
              <div style={{ height: "auto", width: "auto", float: "left" }}>
                Weather - {this.props.description}
                <img
                  alt=""
                  src={`http://openweathermap.org/img/w/${this.props.icon}.png`}
                />
              </div>
              <FormControl>
                <InputLabel htmlFor="City Code">City</InputLabel>
                <Select
                  native
                  fullWidth
                  value={this.props.city}
                  onChange={this.handleCityChange("cityCode")}
                  inputProps={{
                    name: "cityCode",
                    id: "age-native-simple"
                  }}
                >
                  {cities.map(city => (
                    <option key={city.code} value={city.code}>
                      {city.name}
                    </option>
                  ))}
                </Select>
              </FormControl>
            </div>
          </Typography>
          <div style={{ textAlign: "left" }}>
            <Typography component="p">wind: {this.props.wind} m/s</Typography>
            <Divider />
            <Typography component="p">
              temperature: {Math.round(this.props.temperature - 273.15)} Celcius
            </Typography>
            <Divider />
            <Typography component="p">
              humidity: {this.props.humidity} %
            </Typography>
          </div>
          <div style={{ height: "1vh", fontSize: "1.3vh", float: "right" }}>
            <span>Updated last at: {this.props.time}</span>
            <IconButton mini="true" onClick={() => this.props.update()}>
              <HistoryIcon />
            </IconButton>
          </div>
        </CardContent>
      </Card>
    );
  }
}

WeatherCard.propTypes = {
  classes: PropTypes.object.isRequired
};

export default withStyles(styles)(WeatherCard);
