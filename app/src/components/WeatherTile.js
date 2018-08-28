import React, { Component } from "react";
import Paper from "@material-ui/core/Paper";
import "../css/Tile.css";
import WeatherCard from "./WeatherCard";
import LinearProgress from "@material-ui/core/LinearProgress";
import moment from "moment";

const styles = theme => ({
  root: {
    ...theme.mixins.gutters(),
    paddingTop: theme.spacing.unit * 2,
    paddingBottom: theme.spacing.unit * 2
  }
});

class WeatherTile extends Component {
  state = {
    isLoading: true,
    description: "",
    wind: 0,
    temperature: 0,
    humidity: 0,
    icon: "",
    city: "London",
    refreshRequested: "false"
  };

  componentDidMount() {
    this.fetchWeather();
  }

  componentDidUpdate(prevProps, prevState) {
    if (this.state.city !== prevState.city || this.state.refreshRequested) {
      this.fetchWeather();
    }
  }

  async fetchWeather() {
    const response = await fetch(
      `https://api.openweathermap.org/data/2.5/weather?q=${
        this.state.city
      }&APPID=fde0635034a7803e52160f061920ff27`
    );
    const body = await response.json();
    const descr = body.weather[0].description;
    const temp = body.main.temp;
    const humidity = body.main.humidity;
    const wind = body.wind.speed;
    const icon = body.weather[0].icon;
    console.warn(body);
    this.setState({
      description: descr,
      wind: wind,
      temperature: temp,
      humidity: humidity,
      icon: icon,
      isLoading: false,
      refreshRequested: false,
      lastUpdateTime: moment().format("MMMM Do YYYY, h:mm:ss a")
    });
  }

  handleCityChange = name => {
    this.setState({ city: String(name) });
  };

  handleUpdate = () => {
    this.setState({ refreshRequested: true });
  };

  render() {
    const { isLoading } = this.state;

    if (isLoading) {
      return <LinearProgress />;
    }
    return (
      <div className="Tile">
        <Paper className={styles.root} elevation={1}>
          <WeatherCard
            description={this.state.description}
            humidity={this.state.humidity}
            wind={this.state.wind}
            icon={this.state.icon}
            temperature={this.state.temperature}
            selectCity={this.handleCityChange}
            update={this.handleUpdate}
            time={this.state.lastUpdateTime}
          />
        </Paper>
      </div>
    );
  }
}

export default WeatherTile;
