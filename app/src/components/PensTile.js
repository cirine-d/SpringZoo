import React, { Component } from "react";
import Paper from "@material-ui/core/Paper";
import AnimalPenData from "./AnimalPenData.js";
import AddIcon from "@material-ui/icons/Add";
import Button from "@material-ui/core/Button";
import AddAnimalPenDialog from "./AddAnimalPenDialog.js";

import "../css/Tile.css";

const styles = theme => ({
  root: {
    ...theme.mixins.gutters(),
    paddingTop: theme.spacing.unit * 2,
    paddingBottom: theme.spacing.unit * 2
  }
});

class PensTile extends Component {
  state = {
    pens: this.props.data,
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
              <h3>Pens</h3>
              <h5>- {this.state.pens.length} pens</h5>
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
            <AnimalPenData data={this.state.pens} />
          </div>
        </Paper>
        <AddAnimalPenDialog
          close={this.handleClose}
          open={this.state.dialogOpen}
          penTypes={this.props.penTypes}
          penNames={this.props.penNames}
          submit={this.props.submit}
          keepers={this.props.keepers}
        />
      </div>
    );
  }
}

export default PensTile;
