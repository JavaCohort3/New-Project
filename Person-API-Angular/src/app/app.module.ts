import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { AppComponent } from './app.component';
import { PersonComponent } from './person/person.component';
import {RouterModule, Routes} from "@angular/router";
import {FormsModule} from "@angular/forms";
import { PersonDetailComponent } from './person-detail/person-detail.component';



const routes: Routes = [
  { path: '', redirectTo: '/people', pathMatch: 'full' },
  { path: '/person/:id', component: PersonDetailComponent },
  { path: '/person', component: PersonComponent }
];

@NgModule({
  declarations: [
    AppComponent,
    PersonComponent,
    PersonDetailComponent
  ],
  imports: [
    FormsModule,
    RouterModule.forRoot(routes),
    BrowserModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
