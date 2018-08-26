import React, { Component } from "react";
import Paper from "@material-ui/core/Paper";
import "../css/Tile.css";
import WeatherCard from "./WeatherCard";
import LinearProgress from "@material-ui/core/LinearProgress";

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
    icon: ""
  };

  async componentDidMount() {
    const response = await fetch(
      "https://api.openweathermap.org/data/2.5/weather?id=2172797&APPID=483d1e767a9ef8510f28061c00e343ad"
    );
    const body = await response.json();
    console.warn(body);
    const descr = body.weather[0].description;
    const temp = body.main.temp;
    const humidity = body.main.humidity;
    const wind = body.wind.speed;
    const icon = body.weather[0].icon;
    this.setState({
      description: descr,
      wind: wind,
      temperature: temp,
      humidity: humidity,
      icon: icon,
      isLoading: false
    });
  }

  render() {
    const { isLoading } = this.state;

    if (isLoading) {
      return <LinearProgress />;
    }
    console.warn({ state: this.state });
    return (
      <div className="Tile">
        <Paper className={styles.root} elevation={1}>
          <WeatherCard
            description={this.state.description}
            humidity={this.state.humidity}
            wind={this.state.wind}
            icon={this.state.icon}
            temperature={this.state.temperature}
          />
        </Paper>
      </div>
    );
  }
}

export default WeatherTile;
