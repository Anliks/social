import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';

const MUSIC_API = 'http://localhost:8080/api/music/';

@Injectable({
  providedIn: 'root'
})

export class MusicUploadService {

  constructor(private http: HttpClient) {}

  getProfileMusic(): Observable<any> {
    return this.http.get(MUSIC_API + 'musicProfile');
  }

  uploadMusicToUser(file: File): Observable<any> {
    const uploadData = new FormData();
    uploadData.append('file', file);

    return this.http.post(MUSIC_API + 'upload', uploadData);
  }

}



