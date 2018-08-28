import React from "react";
import Button from "@material-ui/core/Button";
import TextField from "@material-ui/core/TextField";
import Dialog from "@material-ui/core/Dialog";
import DialogActions from "@material-ui/core/DialogActions";
import DialogContent from "@material-ui/core/DialogContent";
import DialogTitle from "@material-ui/core/DialogTitle";
import Input from "@material-ui/core/Input";
import InputLabel from "@material-ui/core/InputLabel";
import MenuItem from "@material-ui/core/MenuItem";
import FormControl from "@material-ui/core/FormControl";
import Select from "@material-ui/core/Select";
import { Divider } from "@material-ui/core";

export default class AddAnimalPenDialog extends React.Component {
  state = {
    name: "",
    error: null,
    assignedZooKeeper: ""
  };

  handleSubmit = () => {
    if (this.isFormComplete()) {
      this.setState({ error: null });
      let item = {
        name: this.state.name,
        penType: this.state.penType,
        landSpace: this.state.landSpace,
        waterSpace: this.state.waterSpace,
        airSpace: this.state.airSpace,
        assignedZooKeeper: this.state.assignedZooKeeper,
        capacity: 10
      };
      this.props.submit(item, "animalPens");
      this.props.close();
    } else {
      this.setState({ error: "Complete all required fields" });
    }
  };

  handleNameChange = name => event => {
    this.setState({ [name]: event.target.value });
  };

  handlePenTypeChange = name => event => {
    this.setState({ [name]: event.target.value });
  };

  handleLanspaceChange = name => event => {
    this.setState({ [name]: event.target.value });
  };

  handleWaterspaceChange = name => event => {
    this.setState({ [name]: event.target.value });
  };

  handleAirspaceChange = name => event => {
    this.setState({ [name]: event.target.value });
  };

  handleAssignedZooKeeperChange = name => event => {
    this.setState({ assignedZooKeeper: event.target.value });
  };

  getSuitableZooKeeper = () => {
    let list = [];
    this.props.keepers.map(
      keeper =>
        keeper.penTypes.includes(this.state.penType) && list.push(keeper)
    );
    if (list.length === 0) {
      return null;
    } else {
      return list;
    }
  };

  isFormComplete = () => {
    var isComplete = false;
    if (
      (this.state.name !== "") &
      (this.state.penType !== undefined) &
      (this.state.landSpace !== undefined) &
      (this.state.waterSpace !== undefined) &
      (this.state.airSpace !== undefined) &
      (this.state.assignedZooKeeper !== "" || undefined)
    ) {
      isComplete = true;
    }
    console.warn({ state: this.state, status: isComplete });

    return isComplete;
  };

  render() {
    return (
      <Dialog
        open={this.props.open}
        onClose={this.props.close}
        aria-labelledby="form-dialog-title"
      >
        <DialogTitle id="form-dialog-title">New Animal Pen</DialogTitle>
        <DialogContent>
          <TextField
            autoFocus
            margin="dense"
            id="name"
            label="Name"
            fullWidth
            onChange={this.handlePenTypeChange("name")}
          />
          <FormControl>
            <InputLabel htmlFor="Pen Type">Pen Type</InputLabel>
            <Select
              native
              fullWidth
              value={this.state.penType}
              onChange={this.handlePenTypeChange("penType")}
              inputProps={{
                name: "penType",
                id: "age-native-simple"
              }}
            >
              <option value="" />
              {this.props.penTypes.map(type => (
                <option value={type}>{type}</option>
              ))}
            </Select>
          </FormControl>
          <TextField
            autoFocus
            margin="dense"
            id="name"
            label="Land Area of Pen (m2)"
            onChange={this.handleLanspaceChange("landSpace")}
            type="email"
            fullWidth
          />
          <TextField
            autoFocus
            margin="dense"
            id="name"
            label="Water Volume of Pen (m2)"
            onChange={this.handleWaterspaceChange("waterSpace")}
            fullWidth
          />
          <TextField
            autoFocus
            margin="dense"
            id="name"
            label="Air Volume of Pen (m2)"
            onChange={this.handleAirspaceChange("airSpace")}
            fullWidth
          />
          <br />
          <br />
          <Divider />
          <b>Assign ZooKeeper</b>
          <br />
          <br />
          {this.getSuitableZooKeeper() ? (
            <FormControl>
              <InputLabel htmlFor="Pen Type">Assign ZooKeeper</InputLabel>
              <Select
                native
                fullWidth
                value={this.state.assignedZooKeeper}
                onChange={this.handleAssignedZooKeeperChange("assignedPen")}
                inputProps={{
                  name: "penType",
                  id: "age-native-simple"
                }}
              >
                <option value="" />
                {this.getSuitableZooKeeper().map(keeper => (
                  <option value={keeper.name}>{keeper.name}</option>
                ))}
              </Select>
            </FormControl>
          ) : (
            <span>
              There are no suitable ZooKeepers available. Create one and come
              back to this form.
            </span>
          )}
          <br />
          <br />
          <span style={{ color: "red" }}>{this.state.error}</span>
        </DialogContent>
        <DialogActions>
          <Button onClick={this.props.close} color="primary">
            Cancel
          </Button>
          <Button onClick={this.handleSubmit} color="primary">
            Add
          </Button>
        </DialogActions>
      </Dialog>
    );
  }
}
