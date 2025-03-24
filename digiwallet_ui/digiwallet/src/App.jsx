import './App.css'
import { BrowserRouter as Router, Routes, Route } from "react-router-dom";
import Login from "./component/login";
import Main from "./component/main"; 
import Register from './component/register';
function App() {
  return (
    <Router>
      <Routes>
        <Route path="/" element={<Login />} />
        <Route path="/register" element={<Register />} />
        <Route path="/main" element={<Main />} />
      </Routes> 
    </Router>
  );
}

export default App;
