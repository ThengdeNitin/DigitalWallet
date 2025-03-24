import { useForm } from "react-hook-form";
import { useState } from "react";
import { useNavigate } from "react-router-dom";

function Login() {
  const { register, handleSubmit } = useForm();
  const [message, setMessage] = useState("");
  const [messageType, setMessageType] = useState(""); 
  const navigate = useNavigate(); 

  const onSubmit = async (data) => {
    try {
      const response = await fetch(`http://localhost:8080/api/accounts/${data.accountNumber}?password=${data.password}`, {
        method: "GET",
        headers: { "Content-Type": "application/json" },
      });

      console.log("Raw Response:", response);

      if (!response.ok) {
        throw new Error("Invalid credentials or Account not found");
      }

      const result = await response.json();
      setMessage("Login successful!!");
      setMessageType("success");

      setTimeout(() => navigate("/main", { state: { user: result } }), 1000);
      
    } catch (error) {
      setMessage(error.message);
      setMessageType("error");
    }
  };

  return (
    <div className="flex items-center justify-center min-h-screen back-img">
      <div className="w-full max-w-md p-6 shadow-md rounded-3xl bg-amber-50">
        <h2 className="text-2xl font-bold mb-4 text-center">Login</h2>

        {message && (
          <p className={`p-2 text-center rounded ${messageType === "success" ? "bg-green-100 text-green-700" : "bg-red-100 text-red-700"}`}>
            {message}
          </p>
        )}

        <form onSubmit={handleSubmit(onSubmit)} className="space-y-4">
          <div>
            <label className="block font-semibold">Account Number</label>
            <input
              type="text"
              {...register("accountNumber", { required: "Account Number is required" })}
              className="w-full p-2 border rounded"
            />
          </div>

          <div>
            <label className="block font-semibold">Password</label>
            <input
              type="password"
              {...register("password", { required: "Password is required" })}
              className="w-full p-2 border rounded"
            />
          </div>

          <button type="submit" className="w-full bg-blue-500 text-white p-2 rounded hover:bg-blue-600">
            Login
          </button>

          <div className="flex justify-end">
            <button 
              className="text-blue-500 underline hover:text-blue-700" 
              onClick={() => navigate("/register")} 
            >
              Create Account
            </button>
          </div>

        </form>
      </div>
    </div>
  );
}

export default Login;
