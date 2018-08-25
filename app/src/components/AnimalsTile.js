import React, { Component } from "react";
import Paper from "@material-ui/core/Paper";
import DataTable from "./DataTable.js";
import AddIcon from "@material-ui/icons/Add";
import Button from "@material-ui/core/Button";
import "../css/Tile.css";

const styles = theme => ({
  root: {
    ...theme.mixins.gutters(),
    paddingTop: theme.spacing.unit * 2,
    paddingBottom: theme.spacing.unit * 2
  }
});

class AnimalsTile extends Component {
  state = {
    isLoading: true,
    animals: []
  };

  async componentDidMount() {
    const response = await fetch("/api/animals");
    const body = await response.json();
    this.setState({ animals: body, isLoading: false });
  }

  render() {
    const { isLoading } = this.state;

    if (isLoading) {
      return <p>Loading...</p>;
    }

    return (
      <div className="Tile">
        <Paper className={styles.root} elevation={1}>
          <div className="Content-wrapper">
            <div className="Tile-header">
              <h3>Animals</h3>
              <h5>- {this.state.animals.length} animals</h5>
              <Button
                className="button"
                variant="fab"
                mini
                color="primary"
                aria-label="Add"
              >
                <AddIcon />
              </Button>
            </div>
            <DataTable data={this.state.animals} />
          </div>
        </Paper>
      </div>
    );
  }
}

export default AnimalsTile;
