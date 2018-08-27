import React, { Component } from "react";
import Paper from "@material-ui/core/Paper";
import AddIcon from "@material-ui/icons/Add";
import Button from "@material-ui/core/Button";
import AddAnimalDialog from "./AddAnimalDialog.js";
import "../css/Tile.css";
import AnimalData from "./AnimalData.js";

const styles = theme => ({
  root: {
    ...theme.mixins.gutters(),
    paddingTop: theme.spacing.unit * 2,
    paddingBottom: theme.spacing.unit * 2
  }
});

class AnimalsTile extends Component {
  state = {
    animals: this.props.data,
    speciesKnown: this.props.species,
    dialogOpen: false
  };

  handleClickOpen = () => {
    this.setState({ dialogOpen: true });
  };

  handleClose = () => {
    this.setState({ dialogOpen: false });
  };

  render() {
    return (
      <div className="Tile">
        <Paper className={styles.root} elevation={1}>
          <div className="Content-wrapper">
            <div className="Tile-header">
              <h3>Animals</h3>
              <h5>- {this.state.animals.length} animals</h5>
              <h5>- {this.state.speciesKnown.length} species</h5>

              <Button
                className="button"
                variant="fab"
                mini
                color="primary"
                aria-label="Add"
                onClick={this.handleClickOpen}
              >
                <AddIcon />
              </Button>
            </div>
            <AnimalData data={this.state.animals} />
          </div>
        </Paper>
        <AddAnimalDialog
          close={this.handleClose}
          open={this.state.dialogOpen}
          species={this.state.speciesKnown}
          pens={this.props.pens}
          penTypes={this.props.penTypes}
          animalNames={this.props.animalNames}
          submit={this.props.submit}
        />
      </div>
    );
  }
}

export default AnimalsTile;
