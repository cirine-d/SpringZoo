import React, { Component } from "react";
import logo from "./lion-head.png";
import "./css/App.css";
import StaffTile from "./components/StaffTile.js";
import PensTile from "./components/PensTile";
import AnimalsTile from "./components/AnimalsTile.js";
import WeatherTile from "./components/WeatherTile.js";
import LinearProgress from "@material-ui/core/LinearProgress";

class App extends Component {
  constructor(props) {
    super(props);
    this.state = {
      isLoading: true,
      animalData: [],
      penData: [],
      staffData: []
    };
    this.handleAnimalSubmit = this.handleAnimalSubmit.bind(this);
    this.handlePenSubmit = this.handlePenSubmit.bind(this);
    this.handleAnimalStaffSubmit = this.handleStaffSubmit.bind(this);
  }

  async componentDidMount() {
    const animalResponse = await fetch("/api/animals");
    const animals = await animalResponse.json();
    const pensResponse = await fetch("/api/animalPens");
    const pens = await pensResponse.json();
    const staffResponse = await fetch("/api/staff");
    const staff = await staffResponse.json();

    this.setState({
      animalData: animals,
      penData: pens,
      staffData: staff,
      isLoading: false
    });
  }

  async handleAnimalSubmit(item, target) {
    console.warn({ item });
    await fetch(`/api/${target}`, {
      method: "POST",
      headers: {
        Accept: "application/json",
        "Content-Type": "application/json"
      },
      body: JSON.stringify(item)
    });

    await fetch(`/api/animalPens/${item.assignedPen}`, {
      method: "PUT",
      headers: {
        Accept: "application/json",
        "Content-Type": "application/json"
      },
      body: item.name
    });

    window.location.reload();
  }

  async handleStaffSubmit(item, target) {
    await fetch(`/api/${target}`, {
      method: "POST",
      headers: {
        Accept: "application/json",
        "Content-Type": "application/json"
      },
      body: JSON.stringify(item)
    });

    window.location.reload();
  }

  async handlePenSubmit(item, target) {
    console.warn("yeaa");
    await fetch(`/api/${target}`, {
      method: "POST",
      headers: {
        Accept: "application/json",
        "Content-Type": "application/json"
      },
      body: JSON.stringify(item)
    });

    await fetch(`/api/staff/${item.assignedZooKeeper}`, {
      method: "PUT",
      headers: {
        Accept: "application/json",
        "Content-Type": "application/json"
      },
      body: item.name
    });

    window.location.reload();
  }

  render() {
    const { animalData, penData, staffData, isLoading } = this.state;

    if (isLoading) {
      return <LinearProgress />;
    }

    var speciesKnown = [];
    animalData.map(
      x => !speciesKnown.includes(x.species) && speciesKnown.push(x.species)
    );

    var animalNames = [];
    animalData.map(x => animalNames.push(x.name));

    var penNames = [];
    penData.map(x => penNames.push(x.name));

    var staffNames = [];
    staffData.map(x => staffNames.push(x.name));

    var penTypes = [];
    penData.map(x => !penTypes.includes(x.penType) && penTypes.push(x.penType));

    var availablePens = [];
    penData.map(x => x.capacity !== 0 && availablePens.push(x));

    return (
      <div className="App">
        <header className="App-header">
          <img src={logo} className="App-logo" alt="logo" />
          <div className="App-title">
            <h1>Welcome to the Zoo</h1>
          </div>
        </header>
        <div className="Tile-wrapper">
          <AnimalsTile
            data={animalData}
            species={speciesKnown}
            pens={availablePens}
            penTypes={penTypes}
            animalNames={animalNames}
            submit={this.handleAnimalSubmit}
          />
          <PensTile
            data={penData}
            submit={this.handlePenSubmit}
            penTypes={penTypes}
            keepers={staffData}
          />
          <StaffTile
            data={staffData}
            penTypes={penTypes}
            submit={this.handleStaffSubmit}
          />
          <WeatherTile />
        </div>
      </div>
    );
  }
}

export default App;
