import { Component, OnInit } from '@angular/core';
import { Person } from '../person';
import { PersonService } from '../service/person.service';
import { Location } from "@angular/common";

@Component({
  selector: 'app-person',
  templateUrl: './person.component.html',
  styleUrls: ['./person.component.css']
})
export class PersonComponent implements OnInit {
  person = new Person();
  submit = false;

  constructor(private personService: PersonService, private  location:Location) { }

  ngOnInit() {
    let url = window.location.href,
        person_id = Number(url.replace(/.*\/(\d+)/, '$1'));

    if (person_id) {
      this.personService.get(person_id).subscribe(newPerson => {
        this.person = newPerson;

        document.getElementById('title').innerHTML = `Edit Person (id: ${this.person.id})`;
        document.getElementById('save').innerHTML = 'Update';
      });
    }
  }

  create(): any {
    this.personService.create(this.person)
      .subscribe(person => this.person = person);
  }

  onSubmit() {
    let inputs = document.getElementsByTagName('input'),
        empty = false;

    for (let i = 0; i < inputs.length; i++) {
      if (!inputs[i].value.trim()) {
        empty = true;
      }
    }

    if (empty) {
      alert("Make sure you have filled in all fields.");
      return false;
    }

    this.submit = true;
    this.create();
  }

  goBack(){
    this.location.back();
  }


}
