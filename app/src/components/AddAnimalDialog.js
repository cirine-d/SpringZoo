import React from "react";
import Button from "@material-ui/core/Button";
import TextField from "@material-ui/core/TextField";
import Dialog from "@material-ui/core/Dialog";
import DialogActions from "@material-ui/core/DialogActions";
import DialogContent from "@material-ui/core/DialogContent";
import DialogTitle from "@material-ui/core/DialogTitle";
import Input from "@material-ui/core/Input";
import InputLabel from "@material-ui/core/InputLabel";
import FormControlLabel from "@material-ui/core/FormControlLabel";
import MenuItem from "@material-ui/core/MenuItem";
import FormControl from "@material-ui/core/FormControl";
import Switch from "@material-ui/core/Switch";
import Select from "@material-ui/core/Select";
import Chip from "@material-ui/core/Chip";
import { Divider } from "@material-ui/core";

export default class AddAnimalDialog extends React.Component {
  state = {
    name: "",
    penType: "",
    animalsCompatibleWith: [],
    auto: false
  };

  handleSubmit = () => {
    let item = {
      name: this.state.name,
      species: this.state.species,
      penType: [this.state.penType],
      animalsCompatibleWith: this.state.animalsCompatibleWith,
      landSpace: this.state.landSpace,
      waterSpace: this.state.waterSpace,
      airSpace: this.state.airSpace
    };
    this.props.submit(item, "animals");
    this.props.close();
  };

  handleAutoChange = name => event => {
    this.setState({ [name]: event.target.checked });
  };

  handleNameChange = name => event => {
    this.setState({ [name]: event.target.value });
  };

  handleSpeciesChange = name => event => {
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

  handleCompatibilityChange = event => {
    this.setState({ animalsCompatibleWith: event.target.value });
  };

  handleAssignedPenChange = name => event => {
    this.setState({ [name]: event.target.value });
  };

  getAssignedSpecies = pen => {
    let list = [];
    pen.animals
      ? pen.animals.map(
          animal => !list.includes(animal.species) && list.push(animal.species)
        )
      : null;
    return list;
  };

  validatePen = pen => {
    var isSuitable = false;
    if (
      (pen.penType === this.state.penType) &
      (pen.landSpace >= this.state.landSpace) &
      (pen.waterSpace >= this.state.waterSpace) &
      (pen.airSpace >= this.state.airSpace)
    ) {
      isSuitable = true;
      this.getAssignedSpecies(pen).map(
        species =>
          !this.state.animalsCompatibleWith.includes(species) &&
          (isSuitable = false)
      );
      console.warn(this.getAssignedSpecies(pen));

      return isSuitable;
    }
  };

  getSuitablePen() {
    let list = [];
    console.warn(this.props.pens);
    this.props.pens.map(pen => this.validatePen(pen) && list.push(pen));
    if (list.length === 0) {
      return null;
    } else {
      return list;
    }
  }

  render() {
    console.warn(this.getSuitablePen());
    return (
      <Dialog
        open={this.props.open}
        onClose={this.props.close}
        aria-labelledby="form-dialog-title"
      >
        <DialogTitle id="form-dialog-title">New Animal</DialogTitle>
        <DialogContent>
          <TextField
            autoFocus
            margin="dense"
            id="name"
            label="Name"
            fullWidth
            onChange={this.handlePenTypeChange("name")}
          />
          <TextField
            autoFocus
            margin="dense"
            id="name"
            label="Species"
            onChange={this.handleSpeciesChange("species")}
            fullWidth
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
              {this.props.penTypes.map(type => (
                <option value={type}>{type}</option>
              ))}
            </Select>
          </FormControl>
          <TextField
            autoFocus
            margin="dense"
            id="name"
            label="Land Area of Pen"
            onChange={this.handleLanspaceChange("landSpace")}
            type="email"
            fullWidth
          />
          <TextField
            autoFocus
            margin="dense"
            id="name"
            label="Water Volume of Pen"
            onChange={this.handleWaterspaceChange("waterSpace")}
            fullWidth
          />
          <TextField
            autoFocus
            margin="dense"
            id="name"
            label="Air Volume of Pen"
            onChange={this.handleAirspaceChange("airSpace")}
            fullWidth
          />
          <FormControl>
            <InputLabel htmlFor="select-multiple-chip">Chip</InputLabel>
            <Select
              fullWidth
              multiple
              value={this.state.animalsCompatibleWith}
              onChange={this.handleCompatibilityChange}
              input={<Input id="select-multiple-chip" />}
              renderValue={selected => (
                <div>
                  {selected.map(value => (
                    <Chip key={value} label={value} />
                  ))}
                </div>
              )}
            >
              {this.props.species.map(name => (
                <MenuItem key={name} value={name}>
                  {name}
                </MenuItem>
              ))}
            </Select>
          </FormControl>
          <br />
          <br />
          <Divider />
          <br />
          <b>Assign to pen</b>
          <br />
          <br />
          {this.getSuitablePen() ? (
            <div>
              <FormControl>
                <InputLabel htmlFor="Pen Type">Assigned Pen</InputLabel>
                <Select
                  native
                  fullWidth
                  value={this.state.assignedPen}
                  onChange={this.handleAssignedPenChange("assignedPen")}
                  inputProps={{
                    name: "penType",
                    id: "age-native-simple"
                  }}
                >
                  {this.getSuitablePen().map(pen => (
                    <option value={pen.name}>{pen.name}</option>
                  ))}
                </Select>
              </FormControl>
              <FormControlLabel
                control={
                  <Switch
                    checked={this.state.auto}
                    onChange={this.handleAutoChange("auto")}
                    value="auto"
                  />
                }
                label="Auto-Assign"
              />
            </div>
          ) : (
            <span>
              There are no suitable pens available. Create one and come back to
              this form.
            </span>
          )}
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
