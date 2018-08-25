import React, { Component } from "react";
import Paper from "@material-ui/core/Paper";
import "../css/Tile.css";

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
    weather: [],
    temp: {}
  };

  async componentDidMount() {
    const response = await fetch(
      "https://api.openweathermap.org/data/2.5/weather?id=2172797&APPID=483d1e767a9ef8510f28061c00e343ad"
    );
    const body = await response.json();
    console.warn(body);
    this.setState({ weather: { ...body }, isLoading: false });
  }

  render() {
    const { isLoading } = this.state;

    if (isLoading) {
      return <p>Loading...</p>;
    }
    console.warn({ state: this.state });
    return (
      <div className="Tile">
        <Paper className={styles.root} elevation={1}>
          <div className="Content-wrapper">
            <div className="Tile-header">
              <h3>Weather</h3>
              {this.state.weather}
            </div>
          </div>
        </Paper>
      </div>
    );
  }
}

export default WeatherTile;
