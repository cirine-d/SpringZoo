import React, { Component } from "react";
import logo from "./lion-head.png";
import "./css/App.css";
import StaffTile from "./components/StaffTile.js";
import PensTile from "./components/PensTile";
import AnimalsTile from "./components/AnimalsTile.js";
import WeatherTile from "./components/WeatherTile.js";

class App extends Component {
  state = {
    // isLoading: true
  };

  // async componentDidMount() {
  //   const response = await fetch("/api/animals");
  //   const body = await response.json();
  //   this.setState({ animals: body, isLoading: false });
  // }

  render() {
    // const { animals, isLoading } = this.state;

    // if (isLoading) {
    //   return <p>Loading...</p>;
    // }

    return (
      <div className="App">
        <header className="App-header">
          <img src={logo} className="App-logo" alt="logo" />
          <h1 className="App-title">Welcome to the Zoo</h1>
        </header>
        <div className="Tile-wrapper">
          <AnimalsTile />
          <PensTile />
          <StaffTile />
          <StaffTile />

          {/* <WeatherTile /> */}
        </div>
      </div>
    );
  }
}

export default App;
