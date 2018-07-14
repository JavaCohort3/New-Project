import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppComponent } from './app.component';
import { PersonComponent } from './person/person.component';
import {RouterModule, Routes} from "@angular/router";
import {FormsModule} from "@angular/forms";
import {IndexComponent} from "./index/index.component";
import {PersonDetailComponent} from "./person-detail/person-detail.component";
import {PersonService} from "./service/person.service";
import {HttpClientModule} from "@angular/common/http";

const routes: Routes = [
  { path: '', component: IndexComponent },
  { path: 'create', component: PersonComponent },
  { path: 'edit/:id', component: PersonComponent},
  { path: 'bio/:id', component: PersonDetailComponent }
];

@NgModule({
  declarations: [
    AppComponent,
    PersonComponent,
    IndexComponent,
    PersonDetailComponent
  ],exports : [RouterModule],
  imports: [
    FormsModule,
    RouterModule.forRoot(routes),
    BrowserModule,
    HttpClientModule
  ],
  providers: [PersonService],
  bootstrap: [AppComponent]
})
export class AppModule { }
