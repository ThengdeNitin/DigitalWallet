import { useForm } from "react-hook-form";
import { useState } from "react";
import { useNavigate } from "react-router-dom";

function AccountForm() {
  const { register, handleSubmit, reset, formState: { errors } } = useForm();
  const [message, setMessage] = useState("");
  const [messageType, setMessageType] = useState(""); 
  const navigate = useNavigate();

  const onSubmit = async (data) => {
    try {
      const response = await fetch("http://localhost:8080/api/accounts", {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify(data),
      });

      if (!response.ok) {
        const errorData = await response.json();
        throw new Error(errorData.message || "Account already exists");
      }

      setMessage("Account created successfully!");
      setMessageType("success");
      reset();

      setTimeout(() => navigate("/"), 1000);
    } catch (error) {
      setMessage(error.message);
      setMessageType("error");
    }
  };

  return (
    <div className="flex items-center justify-center min-h-screen back-img-register">
      <div className="max-w-lg w-full p-6 bg-white shadow-md rounded-lg">
        <h2 className="text-2xl font-bold mb-4 text-center">Create Account</h2>

        {message && (
          <p className={`p-2 text-center rounded ${messageType === "success" ? "bg-green-100 text-green-700" : "bg-red-100 text-red-700"}`}>
            {message}
          </p>
        )}

        <form onSubmit={handleSubmit(onSubmit)} className="space-y-4">
          <div>
            <label className="block font-semibold">Account Number</label>
            <input
              type="number"
              {...register("accountNumber", { required: "Account Number is required" })}
              className="w-full p-2 border rounded"
              required
            />
            {errors.accountNumber && <p className="text-red-600 text-sm">{errors.accountNumber.message}</p>}
          </div>

          <div>
            <label className="block font-semibold">Account Holder Name</label>
            <input
              type="text"
              {...register("accountHolderName", { required: "Account Holder Name is required" })}
              className="w-full p-2 border rounded"
              required
            />
            {errors.accountHolderName && <p className="text-red-600 text-sm">{errors.accountHolderName.message}</p>}
          </div>

          <div>
            <label className="block font-semibold">Balance</label>
            <input
              type="number"
              {...register("balance", { required: "Balance is required" })}
              className="w-full p-2 border rounded"
              required
            />
            {errors.balance && <p className="text-red-600 text-sm">{errors.balance.message}</p>}
          </div>

          <div>
            <label className="block font-semibold">Password</label>
            <input
              type="password"
              {...register("password", {
                required: "Password is required",
                pattern: {
                  value: /^(?=.*[A-Z])(?=.*[@$!%*?&])[A-Za-z\d@$!%*?&]{6,}$/,
                  message: "Must include 1 uppercase letter & 1 special character",
                },
              })}
              className="w-full p-2 border rounded"
              required
            />
            {errors.password && <p className="text-red-600 text-sm">{errors.password.message}</p>}
          </div>

          <button type="submit" className="w-full bg-blue-500 text-white p-2 rounded hover:bg-blue-600">
            Create Account
          </button>
        </form>
      </div>
    </div>
  );
}

export default AccountForm;
