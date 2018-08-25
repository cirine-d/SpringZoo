import React, { Component } from "react";
import Paper from "@material-ui/core/Paper";
import DataTable from "./DataTable.js";
import AddIcon from "@material-ui/icons/Add";
import Button from "@material-ui/core/Button";
import "../css/Tile.css";

class StaffTile extends Component {
  state = {
    isLoading: true,
    staffMembers: []
  };

  async componentDidMount() {
    const response = await fetch("/api/staff");
    const body = await response.json();
    this.setState({ staffMembers: body, isLoading: false });
  }

  render() {
    const { isLoading } = this.state;

    if (isLoading) {
      return <p>Loading...</p>;
    }

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
              >
                <AddIcon />
              </Button>
            </div>
            <DataTable data={this.state.staffMembers} />
          </div>
        </Paper>
      </div>
    );
  }
}

export default StaffTile;
