import React, { Component } from "react";
import Paper from "@material-ui/core/Paper";
import StaffData from "./StaffData.js";
import AddIcon from "@material-ui/icons/Add";
import Button from "@material-ui/core/Button";
import AddZooKeeperDialog from "./AddZooKeeperDialog.js";
import "../css/Tile.css";

class StaffTile extends Component {
  state = {
    staffMembers: this.props.data,
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
        <Paper className="Paper" elevation={1}>
          <div className="Content-wrapper">
            <div className="Tile-header">
              <h3>Staff</h3>
              <h5>- {this.state.staffMembers.length} zoo keepers</h5>
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
            <StaffData data={this.state.staffMembers} />
          </div>
        </Paper>
        <AddZooKeeperDialog
          close={this.handleClose}
          open={this.state.dialogOpen}
          species={this.state.speciesKnown}
          pens={this.props.pens}
          penTypes={this.props.penTypes}
          staffNames={this.props.staffNames}
          submit={this.props.submit}
        />
      </div>
    );
  }
}

export default StaffTile;
