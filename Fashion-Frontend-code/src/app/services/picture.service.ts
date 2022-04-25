import {HttpClient, HttpHeaders} from '@angular/common/http';
import { Injectable } from '@angular/core';
import {Observable} from 'rxjs';
import { Picture } from '../models/picture';


const  header = new HttpHeaders({
  'Content-Type': 'application/json',
  'Access-Control-Allow-Origin': '*',
  'Access-Control-Allow-Headers': 'Content-Type',
  'Access-Control-Allow-Methods': 'GET,POST,OPTIONS,DELETE,PUT',
  'Access-Control-Allow-Credentials': 'true',
  'Authorization': 'Bearer szdp79a2kz4wh4frjzuqu4sz6qeth8m3',
});
@Injectable({
  providedIn: 'root'
})
export class PictureService {
  private readonly API_URL = 'http://localhost:8000/api/auth/picture';
  private readonly API_URL1 = 'http://localhost:8000/api/auth/picture1';


  constructor(private http: HttpClient) { }

  getListPicture(): Observable<Picture[]> {
    return this.http.get<Picture[]>(`${this.API_URL}`);
  }
  getPictureById(id: number): Observable<Picture> {
    return this.http.get<Picture>(`${this.API_URL}/${id}`);
  }
  // createPicture(picture: Picture): Observable<Picture> {
  //   return this.http.post<Picture>(this.API_URL, picture);
  // }
  postPicture(preview): Observable<Picture> {
    console.log(preview);
    return this.http.post<Picture>(`${this.API_URL}/addPicture`, {
      file: preview
    }, {headers: header});
  }
  deletePicture(pictureId: number): Observable<any> {
    return this.http.delete(`${this.API_URL}/${pictureId}`);
  }
  putPicture(picture: Picture): Observable<Picture> {
    return this.http.put<Picture>(`${this.API_URL}/${picture.pictureId}`, picture);
  }
  // test get img
  getListPicture1(id: number): Observable<Picture[]> {
    return this.http.get<Picture[]>(`${this.API_URL1}/${id}`);
  }
}
