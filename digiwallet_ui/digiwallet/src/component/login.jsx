import { useForm } from "react-hook-form";
import { useState } from "react";
import { useNavigate } from "react-router-dom";

function Login() {
  const { register, handleSubmit } = useForm();
  const [message, setMessage] = useState("");
  const navigate = useNavigate(); // Hook for navigation

  const onSubmit = async (data) => {
    try {
      const response = await fetch(`http://localhost:8080/api/accounts/${data.accountNumber}?password=${data.password}`, {
        method: "GET",
        headers: { "Content-Type": "application/json" },
      });

      console.log("Raw Response:", response); // Logs the raw response

      if (!response.ok) {
        throw new Error("Invalid credentials or account not found");
      }

      const result = await response.json(); // Expect JSON response

      console.log("Response JSON:", result); // Logs the entire JSON response

      // Redirect to Main page and pass user data as state
      navigate("/main", { state: { user: result } });

    } catch (error) {
      setMessage(error.message);
    }
  };

  return (
    <div className="flex items-center justify-center min-h-screen">
      <div className="w-full max-w-md p-6 shadow-md rounded-lg bg-amber-50">
        <h2 className="text-2xl font-bold mb-4 text-center">Login</h2>
        {message && <p className="text-center text-red-500">{message}</p>}
        <form onSubmit={handleSubmit(onSubmit)} className="space-y-4">
          <div>
            <label className="block font-semibold">Account Number</label>
            <input
              type="text"
              {...register("accountNumber", { required: true })}
              className="w-full p-2 border rounded"
            />
          </div>
          <div>
            <label className="block font-semibold">Password</label>
            <input
              type="password"
              {...register("password", { required: true })}
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
