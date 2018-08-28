import React from "react";
import Button from "@material-ui/core/Button";
import TextField from "@material-ui/core/TextField";
import Dialog from "@material-ui/core/Dialog";
import DialogActions from "@material-ui/core/DialogActions";
import DialogContent from "@material-ui/core/DialogContent";
import DialogTitle from "@material-ui/core/DialogTitle";
import InputLabel from "@material-ui/core/InputLabel";
import FormControl from "@material-ui/core/FormControl";
import Select from "@material-ui/core/Select";
import Chip from "@material-ui/core/Chip";
import MenuItem from "@material-ui/core/MenuItem";
import Input from "@material-ui/core/Input";

export default class AddZooKeeperDialog extends React.Component {
  state = {
    name: "",
    penTypes: [],
    error: null
  };

  handleSubmit = () => {
    if (this.isFormComplete()) {
      if (this.isNameUnique()) {
        this.setState({ error: null });
        let item = {
          name: this.state.name,
          penTypes: this.state.penTypes
        };
        this.props.submit(item, "staff");
        this.props.close();
      } else {
        this.setState({ error: "Name is already taken" });
      }
    } else {
      this.setState({ error: "Complete all required fields" });
    }
  };

  handleNameChange = name => event => {
    this.setState({ [name]: event.target.value });
  };

  handlePenTypeChange = event => {
    this.setState({ penTypes: event.target.value });
  };

  isFormComplete = () => {
    var isComplete = false;
    if ((this.state.name !== "") & (this.state.penTypes.length !== 0)) {
      isComplete = true;
    }
    return isComplete;
  };

  isNameUnique = () => {
    var isUnique = true;
    if (this.props.staffNames.includes(this.state.name)) {
      isUnique = false;
    }
    return isUnique;
  };

  render() {
    return (
      <Dialog
        open={this.props.open}
        onClose={this.props.close}
        aria-labelledby="form-dialog-title"
      >
        <DialogTitle id="form-dialog-title">New ZooKeeper</DialogTitle>
        <DialogContent>
          <TextField
            autoFocus
            margin="dense"
            id="name"
            label="Name"
            fullWidth
            onChange={this.handleNameChange("name")}
          />
          <br />
          <br />
          Type of Pen responsible for:
          <br />
          <FormControl>
            <InputLabel htmlFor="select-multiple-chip">Pen Types</InputLabel>
            <Select
              fullWidth
              multiple
              value={this.state.penTypes}
              onChange={this.handlePenTypeChange}
              input={<Input id="select-multiple-chip" />}
              renderValue={selected => (
                <div>
                  {selected.map(value => (
                    <Chip key={value} label={value} />
                  ))}
                </div>
              )}
            >
              {this.props.penTypes.map(type => (
                <MenuItem key={type} value={type}>
                  {type}
                </MenuItem>
              ))}
            </Select>
          </FormControl>
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
